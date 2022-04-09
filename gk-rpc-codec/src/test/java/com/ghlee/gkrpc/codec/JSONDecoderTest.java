package com.ghlee.gkrpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JSONDecoderTest {

    @Test
    public void decode() {
        Decoder decoder = new JSONDecoder();
        Encoder encoder = new JSONEncoder();
        TestBean bean = new TestBean();
        bean.setName("ghlee");
        bean.setAge(23);
        byte[] bytes = encoder.encoder(bean);
        TestBean bean2 = decoder.decode(bytes, TestBean.class);
        assertEquals(bean.getName(), bean2.getName());
        assertEquals(bean.getAge(),bean2.getAge());
    }
}