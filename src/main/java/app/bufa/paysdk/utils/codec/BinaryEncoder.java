package app.bufa.paysdk.utils.codec;


public interface BinaryEncoder {
	 byte[] encode(byte[] pArray) throws EncoderException;
}
