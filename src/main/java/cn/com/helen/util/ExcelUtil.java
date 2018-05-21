package cn.com.helen.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.com.helen.work.Calculate;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtil {
	private static final int COLUNM = 4;
	public static void generateExcelFile(String filePath,String fileName,List<String> containList) throws Exception {
		if(containList==null || containList.size()<1) {
			throw new Exception("没有找到要生成的内容！");
		}
		File folder = new File(filePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		System.out.println("excel file output folder:"+filePath);
		System.out.println("full name:"+filePath+File.separator+fileName);
		File f = new File(filePath+File.separator+fileName);
		f.createNewFile();
		WritableWorkbook wb = Workbook.createWorkbook(f);
		WritableSheet ws = wb.createSheet("算式", 0);
		int count = containList.size();
		int r = count%COLUNM>0?count/COLUNM+1:count/COLUNM;
		int e = 0;
		for(int row=0;row<r;row++) {
			for(int column=0;column<COLUNM;column++) {
				if(e<containList.size()) {
					Label label = new Label(column, row, containList.get(e));
					ws.addCell(label);
					e++;
				}
			}
		}
		wb.write();
		wb.close();
	}
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			list.add(new Integer(i).toString());
			
		}
		try {
			String path = "C:\\Users\\IBM_ADMIN\\Desktop\\home_work";
			File f = new File(path);
			generateExcelFile(f.getParent(), "test.xls", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
