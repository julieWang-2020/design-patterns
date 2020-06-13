package com.wzh.socket.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @description:
 * @author: Wangzh
 * @create: 2020-06-11 10:52
 **/
public class Server {

    public static void main(String[] args) throws IOException {
        final AsynchronousServerSocketChannel serverChannel=AsynchronousServerSocketChannel.open()
                .bind(new InetSocketAddress("127.0.0.1",8888));

        serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel client, Object attachment) {
                serverChannel.accept(null,this);
                try {
                    System.out.println(client.getRemoteAddress());
                    ByteBuffer buffer=ByteBuffer.allocate(1024);
                    // 钩子函数 ----> template
                    client.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();
                            System.out.println(new String(attachment.array(),0,result));
                            client.write(ByteBuffer.wrap("HelloClient".getBytes()));
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            exc.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
            }
        });

       System.in.read();
    }
}
