package app.bufa.paysdk.utils.codec;

import com.alipay.api.internal.util.codec.EncoderException;

public interface Encoder {
	Object encode(Object pObject) throws EncoderException;
}
