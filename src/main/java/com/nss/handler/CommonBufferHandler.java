package com.nss.handler;

import com.nss.packet.PacketUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 接收请求字符串，并将字符串原路返回
 */
public class CommonBufferHandler extends ChannelInboundHandlerAdapter {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf) msg;
            System.out.println(PacketUtil.decode(byteBuf));
            ctx.channel().writeAndFlush(PacketUtil.decode(byteBuf));
        } else {
            System.out.println("收到了包");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
