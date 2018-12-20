package com.nss.main;

import com.nss.handler.CommonBufferHandler;
import com.nss.handler.LengthFrameDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 服务启动器
 */
public class NssServerStarter {

    public void start(int port) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        NioEventLoopGroup boss = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LengthFrameDecoder())
                                .addLast(new CommonBufferHandler());
                    }
                });
        serverBootstrap.bind(port)
                .addListener(future -> {
                   if (future.isSuccess()) {
                       System.out.println("绑定成功");
                   } else {
                       System.out.println("绑定失败");
                   }
                });
    }
}
