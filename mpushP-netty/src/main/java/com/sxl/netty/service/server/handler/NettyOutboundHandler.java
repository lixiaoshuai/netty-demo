package com.sxl.netty.service.server.handler;

import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by lixiaoshuai on 2017/6/21.
 *
 * @mail sxlshuai@foxmail.com
 */
public class NettyOutboundHandler extends ChannelOutboundHandlerAdapter{

    /**
     * Calls {@link ChannelHandlerContext#write(Object, ChannelPromise)} to forward
     * to the next {@link ChannelOutboundHandler} in the {@link ChannelPipeline}.
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     * @param msg
     * @param promise
     */
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx,msg,promise);
        System.out.println("NettyOutboundHandler 送出");
        ReferenceCountUtil.release(msg);
        promise.setSuccess();
    }


}
