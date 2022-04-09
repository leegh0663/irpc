package com.ghlee.gkrpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JSONEncoderTest {

    @Test
    public void encoder() {
        Encoder encoder = new JSONEncoder();
        TestBean bean = new TestBean();
        bean.setName("ghlee");
        bean.setAge(23);
        byte[] bytes = encoder.encoder(bean);
        assertNotNull(bytes);
    }
}