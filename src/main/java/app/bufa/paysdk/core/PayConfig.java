package app.bufa.paysdk.core;

public class PayConfig {
	
	private String appId;
	private String format = "JSON";
	private String signType = "RSA2";
	private String charset = "utf-8";
	private String notifyUrl;
	private String returnUrl;
	private String privateKey;
	private String publicKey;
	
	
	public String getPrivateKey() {
		return privateKey;
	}
	public PayConfig setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
		return this;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public PayConfig setPublicKey(String publicKey) {
		this.publicKey = publicKey;
		return this;
	}
	public String getAppId() {
		return appId;
	}
	public PayConfig setAppId(String appId) {
		this.appId = appId;
		return this;
	}
	public String getFormat() {
		return format;
	}
	public PayConfig setFormat(String format) {
		this.format = format;
		return this;
	}
	public String getSignType() {
		return signType;
	}
	public PayConfig setSignType(String signType) {
		this.signType = signType;
		return this;
	}
	public String getCharset() {
		return charset;
	}
	public PayConfig setCharset(String charset) {
		this.charset = charset;
		return this;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public PayConfig setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
		return this;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public PayConfig setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
		return this;
	}
	
	

}
