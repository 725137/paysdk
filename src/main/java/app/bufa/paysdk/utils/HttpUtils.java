package app.bufa.paysdk.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	public static String post(String url, Map<String, Object> params) {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);

		try {

			List<NameValuePair> nvList = new ArrayList<>();

			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair nv = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
				nvList.add(nv);
			}

			HttpEntity request = new UrlEncodedFormEntity(nvList,"utf-8");
			
			post.setEntity(request);
			HttpEntity entity = null;

			CloseableHttpResponse resp = httpClient.execute(post);

			if (resp.getStatusLine().getStatusCode() >= 200) {
				HttpEntity resEntity = resp.getEntity();
				String message = EntityUtils.toString(resEntity, "utf-8");
				EntityUtils.consume(entity);
				return message;
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				httpClient.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		return null;
	}

}
