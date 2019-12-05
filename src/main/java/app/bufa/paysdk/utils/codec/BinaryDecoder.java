package app.bufa.paysdk.utils.codec;

public interface BinaryDecoder extends Decoder {

	byte[] decode(byte[] pArray) throws DecoderException;
}
