package com.sxl.netty.service.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


/**
 * Created by lixiaoshuai on 2017/6/20.
 *
 * @mail sxlshuai@foxmail.com
 */
public class NettyChannelInit extends ChannelInitializer<SocketChannel> {

    /**
     * This method will be called once the {@link Channel} was registered. After the method returns this instance
     * will be removed from the {@link ChannelPipeline} of the {@link Channel}.
     *
     * @param ch the {@link Channel} which was registered.
     * @throws Exception is thrown if an error occurs. In that case it will be handled by
     *                   {@link #exceptionCaught(ChannelHandlerContext, Throwable)} which will by default close
     *                   the {@link Channel}.
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ch.pipeline().addLast(new NettyChanneHandler())
                   ;

        /*ByteBuf firstMessage;
        System.out.println("服务器initChannel返回数据   ：query time order   |");
        byte[] req = "query time order   |".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
        ch.write(firstMessage);*/
//        ch.config().set

    }
}
