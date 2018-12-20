package com.nss.handler;

import io.netty.handler.codec.FixedLengthFrameDecoder;

public class LengthFrameDecoder extends FixedLengthFrameDecoder {

    public static final Integer FRAMELENGTH = 10;

    public LengthFrameDecoder() {
        super(FRAMELENGTH);
    }
}
