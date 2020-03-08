package com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Test {
	public static void main(String[] args) throws IOException {
			Analysis a = new Analysis();
			File f =  new File();
			List<String>str = f.readFile("./src/com/input.txt");
			String[] list = null;
			for(int i=0;i<str.size();i++) {
				list = f.DivideBlock(str.get(i));
				List<Map> mList=new ArrayList<Map>(); 
				//获取经过处理的种别码和关键字系列集合
				mList=a.GetSortTypeAndValue(list);
				System.out.println("\t当前在第"+ i +"行");
				System.out.println("\t(种别编码,单词符号)");
				for(Map m0:mList){
					String sortType=(String) m0.get("sortType");
					String value=(String) m0.get("value");
					
					System.out.println("\t("+sortType+",\t"+value+")");
					
				}
			
			}

	}
} 