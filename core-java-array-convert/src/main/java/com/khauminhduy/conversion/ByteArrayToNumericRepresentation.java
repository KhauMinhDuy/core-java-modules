package com.khauminhduy.conversion;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Conversion;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

public class ByteArrayToNumericRepresentation {

	public static int convertByteArrayToIntUsingShiftOperator(byte[] bytes) {
		int value = 0;
		for(byte b : bytes) {
			value = (value << 8) + (b & 0xFF);
		}
		return value;
	}
	
	public static byte[] convertIntToByteArrayUsingShiftOperator(int value) {
		byte[] bytes = new byte[Integer.BYTES];
		int length = bytes.length;
		for(int i = 0; i < length; i++) {
			bytes[length - i - 1] = (byte) (value & 0xFF);
			value >>= 8;
		}
		return bytes;
	}
	
	public static long convertByteArrayToLongUsingShiftOperator(byte[] bytes) {
		long value = 0;
		for(byte b : bytes) {
			value <<= 8;
			value |= (b & 0xFF);
		}
		return value;
	}
	
	public static byte[] convertLongToByteArrayUsingShiftOperator(long value) {
		byte[] bytes = new byte[Long.BYTES];
		int length = bytes.length;
		for(int i = 0; i < length; i++) {
			bytes[length - i - 1] = (byte) (value & 0xFF);
			value >>= 8;
		}
		return bytes;
	}
	
	public static float convertByteArrayToFloatUsingShiftOperator(byte[] bytes) {
	// convert bytes to int
		int intValue = 0;
    for (byte b : bytes) {
        intValue = (intValue << 8) + (b & 0xFF);
    }

    // convert int to float
    return Float.intBitsToFloat(intValue);
	}
	
	public static byte[] convertFloatToByteArrayUsingShiftOperator(float value) {
	// convert float to int
    int intValue = Float.floatToIntBits(value);

    // convert int to bytes
    byte[] bytes = new byte[Float.BYTES];
    int length = bytes.length;
    for (int i = 0; i < length; i++) {
        bytes[length - i - 1] = (byte) (intValue & 0xFF);
        intValue >>= 8;
    }
    return bytes;
	}
	
	public static double convertingByteArrayToDoubleUsingShiftOperator(byte[] bytes) {
		long longValue = 0;
    for (byte b : bytes) {
        longValue = (longValue << 8) + (b & 0xFF);
    }

    return Double.longBitsToDouble(longValue);
	}
	
	public static byte[] convertDoubleToByteArrayUsingShiftOperator(double value) {
		long longValue = Double.doubleToLongBits(value);

    byte[] bytes = new byte[Double.BYTES];
    int length = bytes.length;
    for (int i = 0; i < length; i++) {
        bytes[length - i - 1] = (byte) (longValue & 0xFF);
        longValue >>= 8;
    }
    return bytes;
	}
	
	public static int convertByteArrayToIntUsingByteBuffer(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
    buffer.put(bytes);
    buffer.rewind();
    return buffer.getInt();
	}
	
	public static byte[] convertIntToByteArrayUsingByteBuffer(int value) {
		ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
    buffer.putInt(value);
    buffer.rewind();
    return buffer.array();
	}
	
	public static long convertByteArrayToLongUsingByteBuffer(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    buffer.put(bytes);
    buffer.rewind();
    return buffer.getLong();
	}
	
	public static byte[] convertLongToByteArrayUsingByteBuffer(long value) {
		 ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
     buffer.putLong(value);
     buffer.rewind();
     return buffer.array();
	}
	
	public static float convertByteArrayToFloatUsingByteBuffer(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(Float.BYTES);
    buffer.put(bytes);
    buffer.rewind();
    return buffer.getFloat();
	}
	
	public static byte[] convertFloatToByteArrayUsingByteBuffer(float value) {
		 ByteBuffer buffer = ByteBuffer.allocate(Float.BYTES);
     buffer.putFloat(value);
     buffer.rewind();
     return buffer.array();
	}
	
	public static double convertByteArrayToDoubleUsingByteBuffer(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES);
    buffer.put(bytes);
    buffer.rewind();
    return buffer.getDouble();
	}
	
	public static byte[] convertDoubleToByteArrayUsingByteBuffer(double value) {
		ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES);
    buffer.putDouble(value);
    buffer.rewind();
    return buffer.array();
	}
	
	public static int convertByteArrayToIntUsingBigInteger(byte[] bytes) {
		return new BigInteger(bytes).intValue();
	}
	
	public static byte[] convertIntToByteArrayUsingBigInteger(int value) {
		return BigInteger.valueOf(value).toByteArray();
	}
	
	public static long convertByteArrayToLongUsingBigInteger(byte[] bytes) {
		return new BigInteger(bytes).longValue();
	}
	
	public static byte[] convertLongToByteArrayUsingBigInteger(long value) {
		return BigInteger.valueOf(value).toByteArray();
	}
	
	public static float convertByteArrayToFloatUsingBigInteger(byte[] bytes) {
		int intValue = new BigInteger(bytes).intValue();
		return Float.intBitsToFloat(intValue);
	}
	
	public static byte[] convertFloatToByteArrayUsingBigInteger(float value) {
		int intBits = Float.floatToIntBits(value);
		return BigInteger.valueOf(intBits).toByteArray();
	}
	
	public static double convertByteArrayToDoubleUsingBigInteger(byte[] bytes) {
		long longValue = new BigInteger(bytes).longValue();
		return Double.longBitsToDouble(longValue);
	}
	
	public static byte[] convertDoubleToByteArrayUsingBigInteger(double value) {
		long longBits = Double.doubleToLongBits(value);
		return BigInteger.valueOf(longBits).toByteArray();
	}
	
	public static int convertingByteArrayToIntUsingGuava(byte[] bytes) {
		return Ints.fromByteArray(bytes);
	}
	
	public static byte[] convertIntToByteArrayUsingGuava(int value) {
		return Ints.toByteArray(value);
	}
	
	public static long convertByteArrayToLongUsingGuava(byte[] bytes) {
		return Longs.fromByteArray(bytes);
	}
	
	public static byte[] convertLongToByteArrayUsingGuava(long value) {
		return Longs.toByteArray(value);
	}
	
	public static float convertByteArrayToFloatUsingGuava(byte[] bytes) {
		int intValue = Ints.fromByteArray(bytes);
		return Float.intBitsToFloat(intValue);
	}
	
	public static byte[] convertFloatToByteArrayUsingGuava(float value) {
		int intBits = Float.floatToIntBits(value);
		return Ints.toByteArray(intBits);
	}
	
	public static double convertByteArrayToDoubleUsingGuava(byte[] bytes) {
		long longValue = Longs.fromByteArray(bytes);
		return Double.longBitsToDouble(longValue);
	}
	
	public static byte[] convertDoubleToByteArrayUsingGuava(double value) {
		long longBits = Double.doubleToLongBits(value);
		return Longs.toByteArray(longBits);
	}
	
	public static int convertByteArrayToIntUsingCommonsLang(byte[] bytes) {
		byte[] copyBytes = Arrays.copyOf(bytes, bytes.length);
		ArrayUtils.reverse(copyBytes);
		return Conversion.byteArrayToInt(copyBytes, 0, 0, 0, copyBytes.length);
	}
	
	public static byte[] convertIntToByteArrayUsingCommonsLang(int value) {
		byte[] bytes = new byte[Integer.BYTES];
		Conversion.intToByteArray(value, 0, bytes, 0, bytes.length);
		ArrayUtils.reverse(bytes);
		return bytes;
	}
	
	public static long convertByteArrayToLongUsingCommonsLang(byte[] bytes) {
		byte[] copyBytes = Arrays.copyOf(bytes, bytes.length);
    ArrayUtils.reverse(copyBytes);
    return Conversion.byteArrayToLong(copyBytes, 0, 0, 0, copyBytes.length);
	}
	
	public static byte[] convertLongToByteArrayUsingCommonsLang(long value) {
		byte[] bytes = new byte[Long.BYTES];
    Conversion.longToByteArray(value, 0, bytes, 0, bytes.length);
    ArrayUtils.reverse(bytes);
    return bytes;
	}
	
	public static float convertByteArrayToFloatUsingCommonsLang(byte[] bytes) {
		byte[] copyBytes = Arrays.copyOf(bytes, bytes.length);
    ArrayUtils.reverse(copyBytes);
    int intValue = Conversion.byteArrayToInt(copyBytes, 0, 0, 0, copyBytes.length);
    return Float.intBitsToFloat(intValue);
	}
	
	public static byte[] convertFloatToByteArrayUsingCommonsLang(float value) {
		int intValue = Float.floatToIntBits(value);
    byte[] bytes = new byte[Float.BYTES];
    Conversion.intToByteArray(intValue, 0, bytes, 0, bytes.length);
    ArrayUtils.reverse(bytes);
    return bytes;
	}
	
	public static double convertByteArrayToDoubleUsingCommonsLang(byte[] bytes) {
		byte[] copyBytes = Arrays.copyOf(bytes, bytes.length);
    ArrayUtils.reverse(copyBytes);
    long longValue = Conversion.byteArrayToLong(copyBytes, 0, 0, 0, copyBytes.length);
    return Double.longBitsToDouble(longValue);
	}
	
	public static byte[] convertDoubleToByteArrayUsingCommonsLang(double value) {
		long longValue = Double.doubleToLongBits(value);
    byte[] bytes = new byte[Long.BYTES];
    Conversion.longToByteArray(longValue, 0, bytes, 0, bytes.length);
    ArrayUtils.reverse(bytes);
    return bytes;
	}
	
}
