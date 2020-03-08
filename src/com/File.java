package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class File {
	
	
	/**
	 * @param fileSrc �ļ�·��
	 * return list ����һ������list
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
	 * ��ÿһ�е��ַ������֣����ظ��ݿո񻮷ֵ�list
	 *  @param str ����һ�����ַ�
	 * 
	 * */
	public String[] DivideBlock(String str) {
		String[] list;
		list = str.split(" ");
		return list;
	}
	
}
