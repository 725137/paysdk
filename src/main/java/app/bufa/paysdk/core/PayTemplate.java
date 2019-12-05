package app.bufa.paysdk.core;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.bufa.paysdk.utils.Assert;

public abstract class PayTemplate {

	protected PayConfig payConfig = new PayConfig();

	public PayConfig getPayConfig() {
		return payConfig;
	}


	public abstract void doPay(PayRequest payRequest, PayType payType);

	public abstract boolean verifyResult(HttpServletRequest request);

	public void returnOK(HttpServletResponse response) {

		try {
			PrintWriter writer = response.getWriter();
			writer.write("success");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void returnFail(HttpServletResponse response) {

		try {
			PrintWriter writer = response.getWriter();
			writer.write("fail");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkRequestParams(PayRequest pqyResut) {

		Assert.isNotTrimBank(pqyResut.getOutTradeNo(), "outTradeNo");
		Assert.isNotTrimBank(pqyResut.getProductName(), "productName");
		Assert.isGreaterThanZero(pqyResut.getTotalAmount(), "totalAmount");
		Assert.isNotTrimBank(pqyResut.getTimeExpire(), "timeExpire");
	}

	public abstract Boolean isDebug();

	public abstract void setDebug(Boolean isDebug);

	public abstract PayResponse buildResponse(HttpServletRequest request);

}
