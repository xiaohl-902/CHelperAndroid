package com.cpigeon.cpigeonhelper.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * Mnia框架解析返回值
 * Created by Administrator on 2017/7/4.
 */

public class MyDataEncoder extends ProtocolEncoderAdapter {

    @Override
    public void encode(IoSession session, Object message,
                       ProtocolEncoderOutput out) throws Exception {
        IoBuffer value = (IoBuffer) message;
        out.write(value);
        out.flush();

    }
}