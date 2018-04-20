package com.kopever.wechat.sdk;

import java.util.ArrayList;

class ByteGroup {

    private ArrayList<Byte> byteContainer;

    ByteGroup() {
        byteContainer = new ArrayList<>();
    }

    public byte[] toBytes() {
        byte[] bytes = new byte[byteContainer.size()];
        for (int i = 0; i < byteContainer.size(); i++) {
            bytes[i] = byteContainer.get(i);
        }
        return bytes;
    }

    public void addBytes(byte[] bytes) {
        for (byte b : bytes) {
            byteContainer.add(b);
        }
    }

    public int size() {
        return byteContainer.size();
    }

}
