package app.bufa.paysdk.core;

import java.math.BigDecimal;

public class PayRequest {

	/**
	 * 付款金额
	 */
	private BigDecimal totalAmount;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 商品描述
	 */
	private String productDescript;
	/**
	 * 通知地址
	 */
	private String notifyUrl;
	/**
	 * 商户内部定单号
	 */
	private String outTradeNo;

	/**
	 * 订单失效时间
	 */
	private String timeExpire = "2d";

	/**
	 * 即时通知,可能某些平台不支持 ,比如微信
	 */
	private String returnUrl;

	/**
	 * 附加数据,原样返回的
	 * 
	 * @return
	 */
	private String attach;

	/**
	 * 时间戳,毫秒值
	 */
	private long timestamp;

	/**
	 * 返回值
	 */
	private Result result = new Result();

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescript() {
		return productDescript;
	}

	public void setProductDescript(String productDescript) {
		this.productDescript = productDescript;
	}

	public static class ResultCode {

		public static final Integer NO_REQUEST = -1;
		public static final Integer SUCCESS = 0;
		public static final Integer EXCEPTION = 999;
		public static final Integer FAIL = 1;
	}

	public static class Result {

		private Integer code = ResultCode.NO_REQUEST;
		private String msg = "请求未执行";

		private Object data;
		private String h5Html;
		private String qrCode;
		private String pcHtml;

		public String getH5Html() {
			return h5Html;
		}

		public void setH5Html(String h5Html) {
			this.h5Html = h5Html;
		}

		public String getQrCode() {
			return qrCode;
		}

		public void setQrCode(String qrCode) {
			this.qrCode = qrCode;
		}

		public String getPcHtml() {
			return pcHtml;
		}

		public void setPcHtml(String pcHtml) {
			this.pcHtml = pcHtml;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

	}

}
