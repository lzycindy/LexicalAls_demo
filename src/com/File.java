package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class File {
	
	
	/**
	 * @param fileSrc 文件路径
	 * return list 返回一个整串list
	 * @throws IOExecption
	 **/
	public List<String> readFile(String fileSrc) throws IOException{
		List<String>list = new ArrayList<String>();	
		FileReader fileReader = new FileReader(fileSrc);
		BufferedReader br = new BufferedReader(fileReader);
		String strTemp = null;
		while((strTemp = br.readLine()) != null) {
			list.add(strTemp);
		}
		br.close();
		fileReader.close();
		return list;
		
	}
	
	/**
	 * 讲每一行的字符串划分，返回根据空格划分的list
	 *  @param str 代表一整行字符
	 * 
	 * */
	public String[] DivideBlock(String str) {
		String[] list;
		list = str.split(" ");
		return list;
	}
	
}
