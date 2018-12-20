package com.nss;

import com.nss.handler.LengthFrameDecoder;
import com.nss.packet.PacketUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 测试客户端
 */
public class TestClient {

    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LengthFrameDecoder())
                                .addLast(new TestClientInHandler());
                    }
                });
        bootstrap.connect("127.0.0.1", 10057)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        ((ChannelFuture)future).channel().writeAndFlush(PacketUtil.encode("呵呵"));
                    } else {
                        System.out.println("发送失败");
                    }
                });
    }
}
