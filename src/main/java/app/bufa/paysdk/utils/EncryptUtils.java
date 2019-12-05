package app.bufa.paysdk.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import app.bufa.paysdk.core.PayException;
import app.bufa.paysdk.core.SignTypeConstant;
import app.bufa.paysdk.utils.codec.Base64;

public class EncryptUtils {

	public static String rsaSign(String signType, String content, String privateKey, String charset) {

		try {

			KeyFactory keyFactory = KeyFactory.getInstance(SignTypeConstant.SIGN_TYPE_RSA);

			byte[] encodedKey = StreamUtils.readText(new ByteArrayInputStream(privateKey.getBytes())).getBytes();

			encodedKey = Base64.decodeBase64(encodedKey);

			PrivateKey priKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));

			String signAlgorithms = null;

			if (signType.equalsIgnoreCase(SignTypeConstant.SIGN_TYPE_RSA)) {
				signAlgorithms = SignTypeConstant.SIGN_ALGORITHMS;

			} else if (signType.equalsIgnoreCase(SignTypeConstant.SIGN_TYPE_RSA2)) {
				signAlgorithms = SignTypeConstant.SIGN_SHA256RSA_ALGORITHMS;

			}

			java.security.Signature signature = java.security.Signature.getInstance(signAlgorithms);

			signature.initSign(priKey);

			if (charset == null || charset.trim().isEmpty()) {
				signature.update(content.getBytes());
			} else {
				signature.update(content.getBytes(charset));
			}

			byte[] signed = signature.sign();

			return new String(Base64.encodeBase64(signed));

		} catch (Exception e) {
			throw new PayException("RSA私钥格式不正确，请检查是否正确配置了PKCS8格式的私钥");
		}
	}

	public static boolean rsaCheck(String content, String sign, String publicKey, String charset, String signType) {
		try {

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			StringWriter writer = new StringWriter();
			StreamUtils.io(new InputStreamReader(new ByteArrayInputStream(publicKey.getBytes())), writer);

			byte[] encodedKey = writer.toString().getBytes();

			encodedKey = Base64.decodeBase64(encodedKey);

			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

			String signAlgorithms = null;

			if (signType.equalsIgnoreCase(SignTypeConstant.SIGN_TYPE_RSA)) {
				signAlgorithms = SignTypeConstant.SIGN_ALGORITHMS;

			} else if (signType.equalsIgnoreCase(SignTypeConstant.SIGN_TYPE_RSA2)) {
				signAlgorithms = SignTypeConstant.SIGN_SHA256RSA_ALGORITHMS;

			}

			java.security.Signature signature = java.security.Signature.getInstance(signAlgorithms);

			signature.initVerify(pubKey);

			if (StringUtils.isTrimBlank(charset)) {
				signature.update(content.getBytes());
			} else {
				signature.update(content.getBytes(charset));
			}

			return signature.verify(Base64.decodeBase64(sign.getBytes()));
		} catch (Exception e) {
			throw new PayException("RSAcontent = " + content + ",sign=" + sign + ",charset=" + charset);
		}
	}

}
