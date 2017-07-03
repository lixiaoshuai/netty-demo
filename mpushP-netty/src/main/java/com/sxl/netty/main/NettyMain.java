package com.sxl.netty.main;

import com.sxl.netty.service.server.handler.NettyChannelInit;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by sxl on 2017/6/19.
 *
 * @mail sxlshuai@foxmail.com
 */
public class NettyMain {

    private final int port;

    public NettyMain(int port) {
        this.port = port;


    }

    public void bind() throws InterruptedException {
        // 配置NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();// 连接线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();// 处理线程组
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            bootstrap.childHandler(new NettyChannelInit());
            // 绑定端口，同步等待成功
            ChannelFuture future = bootstrap.bind(port).sync();
            // 等待服务端监听端口关闭，等待服务端链路关闭之后main函数才退出
            future.channel().closeFuture().sync();
        } finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


    public static void main(String[] args) throws Exception {
        new NettyMain(9999).bind();
    }


}
