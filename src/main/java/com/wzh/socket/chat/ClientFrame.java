package com.wzh.socket.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author wzh
 * @date 2020-06-13 19:05
 */
public class ClientFrame extends Frame {

    TextArea ta=new TextArea();
    TextField tf=new TextField();

    Channel channel;
    public ClientFrame(){

        this.setSize(600,400);
        this.setLocation(800,20);
        this.setTitle("ClientFrame");
        ta.setEditable(false);
        this.add(ta,BorderLayout.CENTER);
        this.add(tf,BorderLayout.SOUTH);
        tf.addActionListener(new TextFieldActionListener(this));
        this.setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        initSocket();
    }

    public void initSocket(){
        EventLoopGroup group = new NioEventLoopGroup(1);

        Bootstrap b=new Bootstrap();
        try {
            ChannelFuture f=b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer(this))
                    .connect("127.0.0.1",8888).addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            if(!future.isSuccess()){
                                System.out.println("not connected!");
                            } else{
                                System.out.println("connected!");
                                channel = future.channel();
                            }
                        }
                    }).sync();

            System.out.println("...");
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

    }


    public static void main(String[] args) {
        new ClientFrame();
    }
}

class TextFieldActionListener implements ActionListener{

    ClientFrame client;

    public TextFieldActionListener(ClientFrame client){
        this.client=client;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text=client.tf.getText();
//        client.ta.append("\n"+text);
        client.tf.setText("");
        System.out.println("send Msg:"+text);
        client.channel.writeAndFlush(Unpooled.copiedBuffer(text.getBytes()));
    }
}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel>{

    ClientFrame client;
    public ClientChannelInitializer(ClientFrame client){
        this.client=client;
    }
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ChildHandler(client));
    }
}

class ChildHandler extends ChannelInboundHandlerAdapter{
    ClientFrame client;

    public ChildHandler(ClientFrame client) {
        this.client=client;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf= (ByteBuf) msg;
        byte[] bytes=new byte[buf.readableBytes()];
        buf.getBytes(buf.readerIndex(),bytes);
        String receiveText=new String(bytes);
        client.ta.append("\n"+receiveText);
    }

}
