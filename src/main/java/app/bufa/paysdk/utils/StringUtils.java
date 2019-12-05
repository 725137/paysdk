package app.bufa.paysdk.utils;

import java.util.HashMap;
import java.util.Map;

public class StringUtils {

	public static boolean isTrimBlank(String str) {
		return str == null || str.trim().isEmpty();
	}

	public static boolean isNoTrimBlank(String str) {
		return str != null && !str.trim().isEmpty();
	}

	public static Map<String, String> url2Map(String url) {

		Map<String, String> resultMap = new HashMap<String, String>();

		int index = url.indexOf("?");

		if (index == -1) {
			return resultMap;
		}

		url = url.substring(index + 1);

		String[] pList = url.split("&");

		for (String p : pList) {

			if (p.indexOf("=") == -1) {
				return resultMap;
			}
			String[] kv = p.split("=");
			resultMap.put(kv[0], kv[1]);
		}
		return resultMap;
	}

	public static String map2Url(Map<String, Object> map) {

		StringBuffer content = new StringBuffer();

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			content.append(entry.getKey()).append("=").append(String.valueOf(entry.getValue())).append("&");
		}

		String result = content.toString();

		if (result.endsWith("&")) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}
}
