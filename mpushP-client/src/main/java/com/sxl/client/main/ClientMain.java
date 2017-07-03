package com.sxl.client.main;

import com.sxl.client.service.client.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by lixiaoshuai on 2017/6/20.
 *
 * @mail sxlshuai@foxmail.com
 */
public class ClientMain {

    private ScheduledExecutorService executor = Executors
            .newScheduledThreadPool(1);


    private final int port;

    private final String host;

    public ClientMain(String ip,int port ) {
        this.port = port;
        this.host = ip;
    }

    public void bind(){

        EventLoopGroup group = new NioEventLoopGroup();
// 配置客户端NIO线程组


        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ClientHandler());
            // 发起异步连接操作
            ChannelFuture future = bootstrap.connect(host, port).sync();

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void main(String[] args) {
        new ClientMain("127.0.0.1",9999).bind();
    }
}
