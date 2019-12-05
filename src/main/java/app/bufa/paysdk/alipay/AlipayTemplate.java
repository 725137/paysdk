package app.bufa.paysdk.alipay;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;

import app.bufa.paysdk.core.PayRequest;
import app.bufa.paysdk.core.PayResponse;
import app.bufa.paysdk.core.PayTemplate;
import app.bufa.paysdk.core.PayType;
import app.bufa.paysdk.core.TradeStatus;
import app.bufa.paysdk.utils.Assert;
import app.bufa.paysdk.utils.DateUtils;
import app.bufa.paysdk.utils.StringUtils;

public class AlipayTemplate extends PayTemplate {

	private static final String DEV_GATE_WAY = "https://openapi.alipaydev.com/gateway.do";
	private static final String PRD_GATE_WAY = "https://openapi.alipay.com/gateway.do";
	public static final String RET_SUCCESS = "success";
	public static final String RET_FAIL = "fail";
	public static String GATE_WAY = PRD_GATE_WAY;

	public AlipayTemplate() {

	}

	public AlipayTemplate(String appId, String privateKey, String publicKey) {
		this.payConfig.setAppId(appId);
		this.payConfig.setPrivateKey(privateKey);
		this.payConfig.setPublicKey(publicKey);
	}

	@Override
	public void doPay(PayRequest payRequest, PayType payType) {

		checkConfigParams();
		checkRequestParams(payRequest);

		if (payType == PayType.ALIPAY_F2F_USER_SCAN) {
			f2fUserScanReq(payRequest);
		} else if (payType == PayType.ALIPAY_PC) {
			pcReq(payRequest);
		} else if (payType == PayType.ALIPAY_H5) {
			h5Req(payRequest);
		}
	}

	public void pcReq(PayRequest payRequest) {

		AlipayTradePagePayRequest tradeRequest = new AlipayTradePagePayRequest();

		// 封装请求支付信息
		AlipayTradePagePayModel bizModel = new AlipayTradePagePayModel();
		bizModel.setOutTradeNo(payRequest.getOutTradeNo());
		bizModel.setProductCode("FAST_INSTANT_TRADE_PAY");

		bizModel.setTotalAmount(String.valueOf(payRequest.getTotalAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN)));
		bizModel.setSubject(payRequest.getProductName());
		bizModel.setTimeoutExpress(payRequest.getTimeExpire());
		bizModel.setQrPayMode("3");

		tradeRequest.setBizModel(bizModel);
		tradeRequest.setNotifyUrl(payRequest.getNotifyUrl());
		tradeRequest.setReturnUrl(payRequest.getReturnUrl());

		if (StringUtils.isTrimBlank(tradeRequest.getNotifyUrl())) {
			tradeRequest.setNotifyUrl(this.payConfig.getNotifyUrl());
		}
		if (StringUtils.isTrimBlank(tradeRequest.getReturnUrl())) {
			tradeRequest.setReturnUrl(this.payConfig.getReturnUrl());
		}

		AlipayClient client = new DefaultAlipayClient(GATE_WAY, this.getPayConfig().getAppId(),
				this.getPayConfig().getPrivateKey(), this.getPayConfig().getFormat(), this.getPayConfig().getCharset(),
				this.getPayConfig().getPublicKey(), this.getPayConfig().getSignType());

		try {

			AlipayTradePagePayResponse aliResponse = client.pageExecute(tradeRequest);

			payRequest.getResult().setData(aliResponse);

			if (StringUtils.isTrimBlank(aliResponse.getBody())) {
				payRequest.getResult().setCode(PayRequest.ResultCode.FAIL);
				payRequest.getResult().setMsg(aliResponse.getMsg());
				return;
			}

			payRequest.getResult().setCode(PayRequest.ResultCode.SUCCESS);
			payRequest.getResult().setMsg(RET_SUCCESS);
			payRequest.getResult().setH5Html(aliResponse.getBody());

		} catch (AlipayApiException e) {
			e.printStackTrace();
			payRequest.getResult().setCode(PayRequest.ResultCode.EXCEPTION);
			payRequest.getResult().setMsg(e.getMessage());
		}
	}

	public void h5Req(PayRequest payRequest) {

		AlipayTradeWapPayRequest tradeRequest = new AlipayTradeWapPayRequest();

		// 封装请求支付信息
		AlipayTradeWapPayModel bizModel = new AlipayTradeWapPayModel();
		bizModel.setOutTradeNo(payRequest.getOutTradeNo());
		bizModel.setTotalAmount(String.valueOf(payRequest.getTotalAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN)));
		bizModel.setSubject(payRequest.getProductName());
		bizModel.setTimeoutExpress(payRequest.getTimeExpire());
		bizModel.setProductCode("QUICK_WAP_WAY");

		tradeRequest.setBizModel(bizModel);
		tradeRequest.setNotifyUrl(payRequest.getNotifyUrl());
		tradeRequest.setReturnUrl(payRequest.getReturnUrl());

		if (StringUtils.isTrimBlank(tradeRequest.getNotifyUrl())) {
			tradeRequest.setNotifyUrl(this.payConfig.getNotifyUrl());
		}
		if (StringUtils.isTrimBlank(tradeRequest.getReturnUrl())) {
			tradeRequest.setReturnUrl(this.payConfig.getReturnUrl());
		}

		AlipayClient client = new DefaultAlipayClient(GATE_WAY, this.getPayConfig().getAppId(),
				this.getPayConfig().getPrivateKey(), this.getPayConfig().getFormat(), this.getPayConfig().getCharset(),
				this.getPayConfig().getPublicKey(), this.getPayConfig().getSignType());

		try {

			AlipayTradeWapPayResponse aliResponse = client.pageExecute(tradeRequest);

			payRequest.getResult().setData(aliResponse);

			if (StringUtils.isTrimBlank(aliResponse.getBody())) {
				payRequest.getResult().setCode(PayRequest.ResultCode.FAIL);
				payRequest.getResult().setMsg(aliResponse.getMsg());
				return;
			}

			payRequest.getResult().setCode(PayRequest.ResultCode.SUCCESS);
			payRequest.getResult().setMsg(RET_SUCCESS);
			payRequest.getResult().setH5Html(aliResponse.getBody());

		} catch (AlipayApiException e) {
			e.printStackTrace();
			payRequest.getResult().setCode(PayRequest.ResultCode.EXCEPTION);
			payRequest.getResult().setMsg(e.getMessage());
		}
	}

	public void f2fUserScanReq(PayRequest payRequest) {

		AlipayTradePrecreateRequest tradeRequest = new AlipayTradePrecreateRequest();

		AlipayTradePrecreateModel bizModel = new AlipayTradePrecreateModel();
		bizModel.setOutTradeNo(payRequest.getOutTradeNo());
		bizModel.setTotalAmount(String.valueOf(payRequest.getTotalAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN)));
		bizModel.setSubject(payRequest.getProductName());
		bizModel.setTimeoutExpress(payRequest.getTimeExpire());

		tradeRequest.setBizModel(bizModel);

		tradeRequest.setNotifyUrl(payRequest.getNotifyUrl());
		tradeRequest.setReturnUrl(payRequest.getReturnUrl());

		if (StringUtils.isTrimBlank(tradeRequest.getNotifyUrl())) {
			tradeRequest.setNotifyUrl(this.payConfig.getNotifyUrl());
		}
		if (StringUtils.isTrimBlank(tradeRequest.getReturnUrl())) {
			tradeRequest.setReturnUrl(this.payConfig.getReturnUrl());
		}

		AlipayClient client = new DefaultAlipayClient(GATE_WAY, this.getPayConfig().getAppId(),
				this.getPayConfig().getPrivateKey(), this.getPayConfig().getFormat(), this.getPayConfig().getCharset(),
				this.getPayConfig().getPublicKey(), this.getPayConfig().getSignType());

		try {

			AlipayTradePrecreateResponse aliResponse = client.execute(tradeRequest);

			payRequest.getResult().setData(aliResponse);

			if (!"10000".equals(aliResponse.getCode())) {
				payRequest.getResult().setCode(PayRequest.ResultCode.FAIL);
				payRequest.getResult().setMsg(aliResponse.getMsg());
				return;
			}

			payRequest.getResult().setCode(PayRequest.ResultCode.SUCCESS);
			payRequest.getResult().setMsg(RET_SUCCESS);
			payRequest.getResult().setQrCode(aliResponse.getQrCode());

		} catch (AlipayApiException e) {
			e.printStackTrace();
			payRequest.getResult().setCode(PayRequest.ResultCode.EXCEPTION);
			payRequest.getResult().setMsg(e.getMessage());
		}
	}

	@Override
	public boolean verifyResult(HttpServletRequest request) {

		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}

		try {
			return AlipaySignature.rsaCheckV1(params, this.payConfig.getPublicKey(), this.payConfig.getCharset(),
					"RSA2");
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean isDebug() {
		return GATE_WAY.equals(DEV_GATE_WAY);
	}

	@Override
	public void setDebug(Boolean isDebug) {
		GATE_WAY = isDebug ? DEV_GATE_WAY : PRD_GATE_WAY;
	}

	public void checkConfigParams() {

		Assert.isNotTrimBank(payConfig.getAppId(), "appId");
		Assert.isNotTrimBank(payConfig.getPrivateKey(), "privateKey");
		Assert.isNotTrimBank(payConfig.getPublicKey(), "publicKey");
		Assert.isNotTrimBank(payConfig.getCharset(), "charset");
		Assert.isNotTrimBank(payConfig.getFormat(), "format");
		Assert.isNotTrimBank(payConfig.getSignType(), "signType");
	}

	@Override
	public PayResponse buildResponse(HttpServletRequest request) {

		String app_id = request.getParameter("app_id");
		String buyer_pay_amount = request.getParameter("buyer_pay_amount");
		String gmt_create = request.getParameter("gmt_create");
		String out_trade_no = request.getParameter("out_trade_no");
		String gmt_payment = request.getParameter("gmt_payment");
		String subject = request.getParameter("subject");
		String sign_type = request.getParameter("sign_type");
		String total_amount = request.getParameter("total_amount");
		String trade_no = request.getParameter("trade_no");
		String trade_status = request.getParameter("trade_status");
		// {gmt_create=[2019-07-25 03:03:36], charset=[utf-8],
		// seller_email=[nnhrqq3222@sandbox.com], notify_time=[2019-07-25
		// 03:03:36], subject=[苹果],
		// sign=[f1SW+pgB/IpXp0s048Piyokdkn09Gfq8idpfZvoocA20jSq2RrGsgduZsjtRdusJ3WCw9NrU6ySyj7vIDD1Fr+qmGBwrpAX49Zwh/Wlvi+WT5femBcEiBWTv6lmH7Z/9mlo0CzpoE/U8TbO3SP+WFIdD5vecvOQmhY8Lkiik4PX4BU7mFTTHp+JgpAJEF6LbB9l+lvOtCl2yJJURW5eFJ8eJjZNjMZbKTqTk5NRwHC4blK5cBaxaSA0UF/LIuMbY+0w4AaEQV+Hr6PbuJESbpvZPTvsrqZDpNDqJ+L2z+V/dIb4V0Mz76Y/aNa3XRLoJtsznukevHTwwIKzatqK8YQ==],
		// buyer_id=[2088102178237425], version=[1.0],
		// notify_id=[2019072500222030336037421000509207],
		// notify_type=[trade_status_sync], out_trade_no=[471391974315003904],
		// total_amount=[2.00], trade_status=[WAIT_BUYER_PAY],
		// trade_no=[2019072522001437421000127119],
		// auth_app_id=[2016101100660926],
		// buyer_logon_id=[ksb***@sandbox.com], app_id=[2016101100660926],
		// sign_type=[RSA2], seller_id=[2088102179084301]}

		PayResponse pr = new PayResponse();
		pr.setAppId(app_id);
		pr.setCreateDateTime(DateUtils.parse(gmt_create, DateUtils.yyyy_MM_dd_HH_mm_ss));
		pr.setOutTradeNo(out_trade_no);

		if (buyer_pay_amount != null) {
			try {
				pr.setPayAmount(new BigDecimal(buyer_pay_amount));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		pr.setPayDateTime(DateUtils.parse(gmt_payment, DateUtils.yyyy_MM_dd_HH_mm_ss));
		pr.setProductName(subject);
		pr.setSignType(sign_type);
		try {
			pr.setTotalAmount(new BigDecimal(total_amount));
		} catch (Exception e) {
			e.printStackTrace();
		}
		pr.setTradeNo(trade_no);

		if (TradeStatus.TRADE_CLOSED.equals(trade_status) || TradeStatus.TRADE_FINISHED.equals(trade_status)
				|| TradeStatus.TRADE_SUCCESS.equals(trade_status) || TradeStatus.WAIT_BUYER_PAY.equals(trade_status)) {
			pr.setTradeStatus(trade_status);

		} else {
			pr.setTradeStatus(TradeStatus.TRADE_UNKONW);
		}

		return pr;
	}

}
