package app.bufa.paysdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.alibaba.fastjson.JSON;

import app.bufa.paysdk.alipay.AlipayTemplate;
import app.bufa.paysdk.core.PayRequest;
import app.bufa.paysdk.core.PayTemplate;
import app.bufa.paysdk.core.PayType;

/**
 * Hello world!
 *
 */
public class App {

	public static class Hreq implements HttpServletRequest {

		public static Map<String, String> pMap = new HashMap<String, String>();

		public Hreq() {

		}

		@Override
		public Object getAttribute(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Enumeration<String> getAttributeNames() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getCharacterEncoding() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
			// TODO Auto-generated method stub

		}

		@Override
		public int getContentLength() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getContentType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getParameter(String name) {
			// TODO Auto-generated method stub
			return pMap.get(name);
		}

		@Override
		public Enumeration<String> getParameterNames() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String[] getParameterValues(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<String, String[]> getParameterMap() {

			Map<String, String[]> tMap = new HashMap<String, String[]>();

			for (Map.Entry<String, String> entry : pMap.entrySet()) {

				tMap.put(entry.getKey(), new String[] { entry.getValue() });
			}

			return tMap;
		}

		@Override
		public String getProtocol() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getScheme() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getServerName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getServerPort() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public BufferedReader getReader() throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getRemoteAddr() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getRemoteHost() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setAttribute(String name, Object o) {
			// TODO Auto-generated method stub

		}

		@Override
		public void removeAttribute(String name) {
			// TODO Auto-generated method stub

		}

		@Override
		public Locale getLocale() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Enumeration<Locale> getLocales() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isSecure() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public RequestDispatcher getRequestDispatcher(String path) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getRealPath(String path) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getRemotePort() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getLocalName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getLocalAddr() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getLocalPort() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public ServletContext getServletContext() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public AsyncContext startAsync() throws IllegalStateException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
				throws IllegalStateException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isAsyncStarted() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isAsyncSupported() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public AsyncContext getAsyncContext() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public DispatcherType getDispatcherType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getAuthType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Cookie[] getCookies() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getDateHeader(String name) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getHeader(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Enumeration<String> getHeaders(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Enumeration<String> getHeaderNames() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getIntHeader(String name) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getMethod() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getPathInfo() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getPathTranslated() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getContextPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getQueryString() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getRemoteUser() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isUserInRole(String role) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Principal getUserPrincipal() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getRequestedSessionId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getRequestURI() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public StringBuffer getRequestURL() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getServletPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public HttpSession getSession(boolean create) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public HttpSession getSession() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isRequestedSessionIdValid() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isRequestedSessionIdFromCookie() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isRequestedSessionIdFromURL() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isRequestedSessionIdFromUrl() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void login(String username, String password) throws ServletException {
			// TODO Auto-generated method stub

		}

		@Override
		public void logout() throws ServletException {
			// TODO Auto-generated method stub

		}

		@Override
		public Collection<Part> getParts() throws IOException, ServletException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Part getPart(String name) throws IOException, ServletException {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public static void main(String[] args) {
		System.out.println("Hello World!");

		String appId = "2016101100660926";
		String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCSeo9U3cPN8XsaOh5qVBVn5qE0sQVjW7/JU2Ah51hYH0QF1w/np6s4hoC9JOU2//BuN/FmGtHC1v3mGUI0vU2W0WAiiv3X+35BVM49Qp/1PrXj56bzptur3la/6YSR0ZJvj3iAYUQAbA90bSMeq34uUHcHIhgoRhtxvmuRMrJFWIw1eMQtB9cuPWdHAzBY5jIrH9P2JQruk7NrAFk0aOmE6Bko5ENszZnoVWXyIcA/jkUZ1OxclvQUi2eEBX9DtpFJrJ2DHlK7HVjDhuFjd7W68/OQaqLZnH2lRurKrzXak27FYToz1O7bh2dNvRjbdM4duD7Jvk3toepPiAAfquH9AgMBAAECggEBAIp344DldxD7A1VCtbfsLqQeyCuoYCCR/hYShCDosoHzZHlk2Xsy7FAuDgs/kpNQlPeMWN4d4kcet7vKCuUSQwOcgBWHcPm4m5ykz6XnkpDirE16kj9ob+bj8vvOg2zZQWXGqbIqwH7pqB52gSmgElMz66FJoK6Jv63hxYvn8dBMultLoN/LDRxj8dZsrzuQUdHBW4NKdk0XZ7fkXpzRoCH+qNVQt45zcf6WgI9BdJAgfvGahRQLAOzPAptQkX876xfrZlzod/tEJNfsZRmFeEw+aTp/FS/Brt6fKhjTXJN7U7J9hi6f7e1t6v0VyEW5NFhj9zRw9KWpB/vVZzqKDsECgYEA0VoJR05NABeNkZ5QQK0HscmAsFcxMRb23CMz1OQjXo+gnxcWbb5dmnGpiZCiSSwNiF81r9Ewyj9kj6Uoh+FnJNh+YHwG8gyvJ2L05IAum/QpeK2DdwX8fxuMvid3hQPGAG3tdXC5P3qAhYUeZpYTUV84wSC+1EmebCQ/AK3aYi0CgYEAsx4WXmGVWoTAPyKVUXqKjQRmNp0hX1/+996fXvEFJ9Za3Zv/dVj4z+GtAk1/iDF+b3DJXYAWyN9FjDJnA7T7EobhdiuN4myWh9ae//uxH+KSm2k2rwHMXwO3hL9tChxq3aJLBt0fsOTfXDcvaE2dPxGxmFPUSRCFgB/IotF28RECgYEAwLFgzPMzSJxlP905/O3GoUvZWXZH3ulGOwVj1dZO/+CvdP9pM4X+3gM0i1xHI5aatiT8Y2HmyKFkzBq2natIps9mMyS1t+EY7uXUhuW9AOrRqTP8R3eJGu2es//bsODyRL7pzCj/OYBl/3+xpbcI/OLqeEjNnNROLzeFjfh3g9kCgYB3P+DElVNKIHedkPi4dk0n9XXXhmVhihfGDoEsRGqaSyvTvrgk+Ecun6D/l3kAw3LZ3s0xYsXIll1HKYdMAIHno8UXV4fu5zyQ02KoKnmSMNZKgZHWkevulhroVknSIXlEdXhd6Xnf24CZdGQrnQDDrJQARk9fFZDL7F03iR0XAQKBgQCQVfAVNG78kBO7I2xThY7lyE4pe+Ln7CulcGF/5sxAGVsvhb2Edh1vbiHby8KJsvXsH+AY38iZKXfZOOMzw7VFDuIdP9LxkrOV1lYtVUqNOJkKSTXIUarM+gNgnWaP1X+XiIzJVsTYjhrK4x1SYiglEQqxb+7OJ2OfEBFrO1taZQ==";
		String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkXzbKdJbDqSBDzu/J2wC+m8/87ru7kldCih0ZYGAbdCYNnjlUM2YivvqySyo+a2GaJzV9mCEgpEtHlZ2uaBu6XtAaR7rxJKx2QjC7iU43Un3VSEQrjYq1rHJrE4nADVZ55Hy/28nKXgx2W6vZy4fJUeEOrFj9bDktdbGlt5VBfTJsRgGrobRM+cNyPj+SMMHpaW4hVFzrFm717jFHoYiyZDyPT26DqhumnmKoHbAkfAk/8CZ8eNmwVlohg6NNz5sd71L6Cao7BHwens6dFtyI5p8WYFYFAh1eaJirISE1qgVfnR1OW39+LpuN00/rcx9Dys6GI+qaU82xgr5Ho5JaQIDAQAB";

		PayTemplate payTemplate = new AlipayTemplate(appId, privateKey, publicKey);
		payTemplate.setDebug(true);

		PayRequest payRequest = new PayRequest();

		payRequest.setOutTradeNo(String.valueOf(System.currentTimeMillis()));
		payRequest.setProductName("title");
		payRequest.setTotalAmount(new BigDecimal(100));

		payTemplate.doPay(payRequest, PayType.ALIPAY_H5);

		System.out.println(JSON.toJSONString(payRequest.getResult()));

		// Hreq req= new Hreq();
		//
		// String url =
		// "https://api.xx.com/receive_notify.htm?app_id=2016101100660926&gmt_payment=2015-06-11
		// 22:33:59&notify_id=42af7baacd1d3746cf7b56752b91edcj34&seller_email=testyufabu07@alipay.com&notify_type=trade_status_sync&sign=kPbQIjX+xQc8F0/A6/AocELIjhhZnGbcBN6G4MM/HmfWL4ZiHM6fWl5NQhzXJusaklZ1LFuMo+lHQUELAYeugH8LYFvxnNajOvZhuxNFbN2LhF0l/KL8ANtj8oyPM4NN7Qft2kWJTDJUpQOzCzNnV9hDxh5AaT9FPqRS6ZKxnzM=&trade_no=2015061121001004400068549373&out_trade_no=21repl2ac2eOutTradeNo322&gmt_create=2015-06-11
		// 22:33:46&seller_id=2088211521646673&notify_time=2015-06-11
		// 22:34:03&subject=FACE_TO_FACE_PAYMENT_PRECREATE中文&trade_status=TRADE_SUCCESS&sign_type=RSA2";
		//
		// req.pMap = StringUtils.url2Map(url);
		//
		// boolean ok = pClient.verifyResult(req);
		//
		// System.out.println(ok);

	}
}
