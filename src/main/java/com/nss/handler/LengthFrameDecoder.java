package com.nss.handler;

import io.netty.handler.codec.FixedLengthFrameDecoder;

public class LengthFrameDecoder extends FixedLengthFrameDecoder {

    public static final Integer FRAMELENGTH = 50;

    public LengthFrameDecoder() {
        super(FRAMELENGTH);
    }
}
