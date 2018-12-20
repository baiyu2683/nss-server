package com.nss.packet;

import com.nss.handler.LengthFrameDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.CharsetUtil;

public class PacketUtil {

    /**
     * 包最大长度为50
     * @see LengthFrameDecoder
     * @param msg
     * @return
     */
    public static ByteBuf encode(String msg) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(LengthFrameDecoder.FRAMELENGTH);
        byteBuf.writeByte(10);
        byte[] msgBytes = msg.getBytes(CharsetUtil.UTF_8);
        byteBuf.writeBytes(msgBytes, 0, LengthFrameDecoder.FRAMELENGTH - 10);
        return byteBuf;
    }

    public static String decode(ByteBuf byteBuf) {
        byte magicNumber = byteBuf.readByte();
        byte[] msgBytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(msgBytes);
        return new String(msgBytes, CharsetUtil.UTF_8);
    }

}
