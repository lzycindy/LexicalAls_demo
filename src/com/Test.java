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
				//��ȡ����������ֱ���͹ؼ���ϵ�м���
				mList=a.GetSortTypeAndValue(list);
				System.out.println("\t��ǰ�ڵ�"+ i +"��");
				System.out.println("\t(�ֱ����,���ʷ���)");
				for(Map m0:mList){
					String sortType=(String) m0.get("sortType");
					String value=(String) m0.get("value");
					
					System.out.println("\t("+sortType+",\t"+value+")");
					
				}
			
			}

	}
} 