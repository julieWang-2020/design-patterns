package com.wzh.socket.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-06-11 10:28
 **/
public class PoolServer {
    ExecutorService pool = Executors.newFixedThreadPool(50);

    private Selector selector;

    public static void main(String[] args) throws IOException {
        PoolServer server=new PoolServer();
        server.initServer(8888);
        server.listen();
    }

    private void listen() throws IOException {
        while (true){
            // block
            selector.select();

            Iterator<SelectionKey> it=selector.selectedKeys().iterator();
            while (it.hasNext()){
                SelectionKey key=it.next();
                it.remove();
                if(key.isAcceptable()){
                    ServerSocketChannel ssc= (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(key.selector(),SelectionKey.OP_READ);
                }else if (key.isReadable()){
                    key.interestOps(key.interestOps()&(-SelectionKey.OP_READ));
                    pool.execute(new ThreadHandlerChannel(key));
                }
            }
        }
    }

    private void initServer(int port) throws IOException {
        ServerSocketChannel channel=ServerSocketChannel.open();

        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress("127.0.0.1",port));

        this.selector=Selector.open();

        channel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务启动成功！");
    }
}
class ThreadHandlerChannel implements Runnable {
    private SelectionKey key;
    public ThreadHandlerChannel(SelectionKey key) {
        this.key=key;
    }

    @Override
    public void run() {
        SocketChannel  channel=(SocketChannel) key.channel();
        ByteBuffer buffer=ByteBuffer.allocate(512);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        try {
            int size=0;
            while ((size = channel.read(buffer)) > 0){
                buffer.flip();
                baos.write(buffer.array(),0,size);
                buffer.clear();
            }
            baos.close();

            byte[] content=baos.toByteArray();

            ByteBuffer bufferToWrite=ByteBuffer.allocate(content.length);
            bufferToWrite.put(content);
            bufferToWrite.flip();
            channel.write(bufferToWrite);

            if(size == -1){
                channel.close();
            } else {
                key.interestOps(key.interestOps()|SelectionKey.OP_READ);
                key.selector().wakeup();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}