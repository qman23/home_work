package cn.com.helen.work;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

public class Test {
	public static void main(String[] args) throws IOException {

		try {
			List<String> list = Calculate.generateFormulaList(40,2);
			for (String string : list) {
				System.out.println(string);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
