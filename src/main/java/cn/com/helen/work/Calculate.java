package cn.com.helen.work;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import cn.com.helen.util.ExcelUtil;

public class Calculate {

	private static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript"); 
	
	private static int RANGE = 30;
	
	private static int getRandomIntByRange(int range) {
		return new Random().nextInt(range);
	}
	
	private static String getSymbol() {
		return new Random().nextInt(10)%2==0?"+":"-";
	}
	
	/**
	 * generate formula
	 * @return
	 * @throws Exception 
	 */
	private static String generateFormula(int elementCount) throws Exception {
		final int ELE_RANGE = RANGE;
		if(elementCount<2) {
			throw new Exception("算式的成员必须大于两个！");
		}
		StringBuilder sb = new StringBuilder();
		int firstElement = getRandomIntByRange(ELE_RANGE);
		int nextElement = 0;
		sb.append(firstElement);
		for(int i=0;i<elementCount-1;i++){
			int tempResult = -1;
			String symbol = "";
			while(tempResult<0 || tempResult>RANGE){
				symbol = getSymbol();
				nextElement = getRandomIntByRange(ELE_RANGE);
				tempResult=new Integer(jse.eval(firstElement+symbol+nextElement).toString());
			}
			firstElement = tempResult;
			sb.append(symbol);
			sb.append(nextElement);
			
		}
		return sb.toString();
	}
	
	/**
	 * generate formula list
	 * @param formulaCount
	 * @param elementCount
	 * @return
	 * @throws Exception
	 */
	public static List<String> generateFormulaList(int formulaCount,int elementCount) throws Exception{
		List<String> result = new ArrayList<>();
		for(int i=0;i<formulaCount;i++) {
			int calResult = -1;
			String formula = "";
			int count = 0;
			while(calResult<0) {
				formula = generateFormula(elementCount);
				Object r = jse.eval(formula);
				calResult = new Integer(r.toString());
				count++;
			}
//			System.out.println("run "+count+" times to get the result!");
			result.add(formula+"=");
			
		}
		return result;
	}
	
	public static void generate(int formulaCount,int elementCount) {
		try {
			List<String> list = generateFormulaList(formulaCount,elementCount);
			String path = "path:"+Calculate.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			int fIndex = path.indexOf("/")+1;
			int lIndex = path.lastIndexOf("/");
			path = path.substring(fIndex, lIndex);
			path = URLDecoder.decode(path, "UTF-8");
			String fileName = "result.xls";
			ExcelUtil.generateExcelFile(path, fileName, list);
			System.out.println("生成题型成功！");
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		if(args==null || args.length<3) {
			throw new Exception("必须提供需要生成算式的个数，算式的元数，以及表达式的范围！");
		}
		
		int formulaCount = new Integer(args[0]);
		int elementCount = new Integer(args[1]);
		Calculate.RANGE = new Integer(args[2]);
		generate(formulaCount,elementCount);
	}
}
