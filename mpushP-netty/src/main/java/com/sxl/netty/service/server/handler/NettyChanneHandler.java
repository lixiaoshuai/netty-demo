package com.sxl.netty.service.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;

import java.util.Date;

/**
 * Created by lixiaoshuai on 2017/6/20.
 *
 * @mail sxlshuai@foxmail.com
 */
public class NettyChanneHandler extends ChannelInboundHandlerAdapter  {



    /**
     * 回写给客户端的信息
     * 
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf firstMessage;
        System.out.println("服务器返回数据   ：Hallo word");
        byte[] req = "Hallo word".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);

        // TODO: 2017/6/29   连接后，写消息到客户端，
        // TODO:  addListener(ChannelFutureListener.CLOSE)写完后便关闭连接
        ctx.writeAndFlush(firstMessage).addListener(ChannelFutureListener.CLOSE);


    }



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // TODO: 2017/6/29     获取客户端信息
      /*  super.channelRead(ctx, msg);
        System.out.println("服务器接收数据 : ");
        ByteBuf buf = (ByteBuf) msg;

        byte[] req = new byte[buf.readableBytes()];// 获得缓冲区可读的字节数
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("服务器接收数据 : " + body);

        String currentTime = "query time order123123";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());

        for (int i=0 ; i<resp.capacity();i++){
            System.out.println(resp.getByte(i));
        }*/
        System.out.println("123");
        ctx.write(msg);// 性能考虑，仅将待发送的消息发送到缓冲数组中，再通过调用flush方法，写入channel中


    }



    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        // TODO: 2017/6/29     获取客户端信息
        super.channelRead(ctx, msg);
        System.out.println("服务器接收数据 : ");
        ByteBuf buf = (ByteBuf) msg;

        byte[] req = new byte[buf.readableBytes()];// 获得缓冲区可读的字节数
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("服务器接收数据 : " + body);

        String currentTime = "query time order123123";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());

        for (int i=0 ; i<resp.capacity();i++){
            System.out.println(resp.getByte(i));
        }
        ctx.write(resp);// 性能考虑，仅将待发送的消息发送到缓冲数组中，再通过调用flush方法，写入channel中

    }


    public static void main(String[] args) {
        ByteBuf  directBuf = Unpooled.directBuffer(16);
        if(!directBuf.hasArray()){
            int len = directBuf.readableBytes();
            byte[] arr = new byte[len];
            directBuf.getBytes(0, arr);
        }
    }
}
