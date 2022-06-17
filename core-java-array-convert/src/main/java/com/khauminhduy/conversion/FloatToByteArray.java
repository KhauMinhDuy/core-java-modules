package com.khauminhduy.conversion;

import java.nio.ByteBuffer;

public class FloatToByteArray {

	public static byte[] floatToByteArray(float value) {
		int intBits = Float.floatToIntBits(value);
		return new byte[] { (byte) (intBits >> 24), (byte) (intBits >> 16), (byte) (intBits >> 8), (byte) (intBits) };
	}
	
	public static float byteArrayToFloat(byte[] bytes) {
		int intBits =  bytes[0] << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
		return Float.intBitsToFloat(intBits);
	}
	
	public static byte[] floatToByteArrayWithByteBuffer(float value) {
		return ByteBuffer.allocate(4).putFloat(value).array();
	}
	
	public static float byteArrayToFloatWithByteBuffer(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getFloat();
	}

}
