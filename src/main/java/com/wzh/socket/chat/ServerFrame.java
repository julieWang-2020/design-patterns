package com.wzh.socket.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author wzh
 * @date 2020-06-13 19:05
 */
public class ServerFrame extends Frame {

    public static ChannelGroup clients=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    TextArea ta=new TextArea();
    ChannelFuture f;
    public ServerFrame(){
        this.setSize(600,400);
        this.setLocation(100,20);
        ta.setEditable(false);
        this.add(ta,BorderLayout.CENTER);
        this.setVisible(true);
        this.setTitle("ServerFrame");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        initServerSocket();
    }

    public void initServerSocket(){
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(2);

        ServerBootstrap server=new ServerBootstrap();
        try {
            f=server.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerChannelInitializer(this))
                    .bind(8888)
                    .sync();
            System.out.println("server started! ");
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("0-----------------");
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
       new ServerFrame();
    }
}

class ServerChannelInitializer extends ChannelInitializer{

    ServerFrame frame;
    public ServerChannelInitializer( ServerFrame frame){
        this.frame=frame;
    }
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline cp=ch.pipeline();
        cp.addLast(new ServerChildHandler(frame));
    }
}

class ServerChildHandler extends ChannelInboundHandlerAdapter{

    ServerFrame frame;
    public ServerChildHandler( ServerFrame frame){
        this.frame=frame;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        frame.clients.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf= (ByteBuf) msg;
        byte[] bytes=new byte[buf.readableBytes()];
        buf.getBytes(buf.readerIndex(),bytes);
        String receiveText=new String(bytes);
        System.out.println("server read!");
        frame.ta.append(receiveText+"\n");
        frame.clients.writeAndFlush(msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        frame.clients.remove(ctx.channel());
        ctx.close();
    }
}
