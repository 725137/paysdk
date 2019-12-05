package app.bufa.paysdk.utils.codec;

public interface Decoder {
	Object decode(Object pObject) throws DecoderException;
}
