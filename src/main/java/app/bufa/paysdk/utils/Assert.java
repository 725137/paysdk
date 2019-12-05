package app.bufa.paysdk.utils;

import app.bufa.paysdk.core.PayException;

public class Assert {

	public static void isNotNull(Object value, String paramsName) {
		if (value == null) {
			throw new PayException("参数不能为空:" + paramsName);
		}
	}

	public static void isNotTrimBank(String value, String paramsName) {
		if (value == null || value.trim().length() == 0) {
			throw new PayException("参数不能为空:" + paramsName);
		}
	}

	public static void isGreaterThanZero(Object value, String paramsName) {
		isNotNull(value, paramsName);

		Number numberValue = null;

		if (value instanceof String) {
			try {
				numberValue = Double.parseDouble(value.toString());
			} catch (Exception e) {
				throw new PayException("数据转换不正确:" + paramsName);
			}
		}

		if (value instanceof Number) {
			numberValue = (Number) value;
		}
		if (numberValue == null || numberValue.intValue() <= 0) {
			throw new PayException("必须大于0:" + paramsName);
		}
	}
}
