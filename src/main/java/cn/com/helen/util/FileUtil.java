package cn.com.helen.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileUtil {
	//input folder
	private final String INPUT_FOLDER;
	
	//output folder
	private final String OUTPUT_FOLDER;

	public FileUtil(String iNPUT_FOLDER, String oUTPUT_FOLDER) {
		super();
		INPUT_FOLDER = iNPUT_FOLDER;
		OUTPUT_FOLDER = oUTPUT_FOLDER;
	}
	/**
	 * read text file contain
	 * @param file
	 * @return
	 */
	private String readTextFile(File file) throws Exception{
		System.out.println("reading the file:"+file.getAbsolutePath());
		if(file.isDirectory()) {
			throw new Exception("can not read the folder!"+file.getAbsolutePath());
		}
		StringBuilder result = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String tmp = null;
			int line_count = 0;
			while((tmp=br.readLine())!=null) {
				if(line_count>0) {
					result.append("\n");
				}
				result.append(tmp);
				line_count++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(br!=null) {
				br.close();
			}
		}
		return result.toString();
	}
	
	/**
	 * write contain to the text file
	 * @param outputFile
	 * @param contain
	 * @throws Exception
	 */
	private void writeToTextFile(File outputFile,String contain) throws Exception{
		System.out.println("write the file:"+outputFile.getAbsolutePath());
		if(outputFile.isDirectory()) {
			throw new Exception("can not write to the folder!"+outputFile.getAbsolutePath());
		}
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(outputFile));
			bw.write(contain);
		}catch(Exception e) {
			throw e;
		}finally {
			if(bw!=null) {
				bw.flush();
				bw.close();
			}
		}
	}
	
	public static void main(String[] args) {
	}
	
}
