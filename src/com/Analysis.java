package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analysis {
	// 创建类型表
	GetSortTypes t = new GetSortTypes();
	
	/**
	 * 判断list是否为合法子串
	 * @param list
	 * @return
	 * */
	public List<Map> GetSortTypeAndValue(String[] list){
		
		String sortType = "",value = "";
		char fc; //首字符
		String fct,word; //fct 第一个首字母类型，word 需要判断的字
		List<Map> rlist = new ArrayList<Map>();
		int l = list.length;
		
		
		for(int i=0;i<l;i++) {
			Map<String,String>map=new HashMap<String,String>();//map保存判断成功的字和种别码
			word = list[i];
			//判空（换行或者长空串)
			if(word == ""|| word == null) continue;
			
			fc = word.charAt(0);
			fct = GetTypes(fc);
			
		// 判断关键字和标识符
			if(fct == "letter") {
				
				//判断是否可能为关键字
				if(fc == 'D'|| fc == 'I' || fc == 'S' || fc == 'E') {
					Map<String,String>m=new HashMap<String,String>();
					m = GetKeyWords(word);
					if(m != null) {
						sortType = m.get("sortType");
						value = word;
					}else {
						
						//判断是否为标准的标识符ID
						if(IsRightId(word)) {
							sortType = ""+t.getTypes("ID");
							value =  word;
						}
						else {
							System.out.println("该符号1"+ word+"不合法");
							
						}
					}
				}else {
					// 首字符为其他字符
					if(IsRightId(word)) {
						sortType = ""+t.getTypes("ID");
						value =  word;
					}else {
						System.out.println("该符号2"+ word+"不合法");
						
					}
				}
			
			}
			//判断字符串
			if(IsString(word)) {
				sortType = "0";
				value = word;
			}
			
			//判断数字
			if(fct == "digit") {
				if(IsNumber(word)) {
					sortType = ""+t.getTypes("NUM");
					value =  word;
				}
			}
			
			//判断符号
			if(fct == "opts") {
				int len = word.length();
				
				//判断单符号
				if(len == 1) {
					if(IsOperator(word.charAt(0))) {
						sortType = ""+t.getTypes(word);
						value =  word;
					}
					else {
						System.out.println("该符号3"+ word+"不合法");
					}
				}else if(len == 2) {
					if(IsDoubleOperator(word)) {
						sortType = ""+t.getTypes(word);
						value =  word;
					}
					else {
						System.out.println("该符号4"+ word+"不合法");
					}
				}
				//判断字符串
				else if(IsString(word)) {
					sortType = "0";
					value = word;
				}
				else {
					System.out.println("该符号5"+ word+"不合法");
				}
			}
			
			// 判空
			if(sortType ==""||value =="") {
				continue;
			}
			
			else {
				map.put("sortType",sortType);
				map.put("value", value);
				rlist.add(map);
				sortType = "";
				value = "";
			}
		}	return rlist;	
	}
	
	
	/**
	 * 输入字符，判断类型
	 * @param c
	 * @return type
	 * 
	 * */
	public String GetTypes(char c) {

		String type = "";
		if(Character.isLetter(c)) {
			type = "letter";
		}else if(Character.isDigit(c)) {
			type = "digit";
		}
		
		else {
			type = "opts";
		}
		return type;
		
	}
	
	/**
	 * 判断是否关键字
	 * @param word
	 * @return Map<String,String>
	 * */
	
	public Map<String,String> GetKeyWords(String word){
		Map<String,String> k = new HashMap<String, String>();
		char fc = word.charAt(0);
		switch(fc) {
		case'D':
			if(word == "DIM"||word.equals("DIM")) {
				String sortType = ""+t.getTypes(word);
				k.put("sortType",sortType );
				k.put("value", "DIM");
			}else if(word == "DO"||word.equals("DO")) {
				String sortType = ""+t.getTypes(word);
				k.put("sortType",sortType );
				k.put("value", "DO");
			}
			else if(word == "DEFAULT"||word.equals("DEFAULT")) {
				String sortType = ""+t.getTypes(word);
				k.put("sortType",sortType );
				k.put("value", "DEFAULT");
			}
			break;
		case 'I':
			if(word == "IF"||word.equals("IF")) {
				String sortType = ""+t.getTypes(word);
				k.put("sortType",sortType );
				k.put("value", "IF");
			}
			break;	
			
			
		case 'S':
			if(word == "STOP"||word.equals("STOP")) {
				String sortType = ""+t.getTypes(word);
				k.put("sortType",sortType );
				k.put("value", "STOP");
			}else if(word == "String"||word.equals("String")) {
				String sortType = ""+t.getTypes(word);
				k.put("sortType",sortType );
				k.put("value", "String");
			}
			else if(word == "STATIC"||word.equals("STATIC")) {
				String sortType = ""+t.getTypes(word);
				k.put("sortType",sortType );
				k.put("value", "STATIC");
			}
			break;	
			
		case 'E':
			if(word == "END"||word.equals("END")) {
				String sortType = ""+t.getTypes(word);
				k.put("sortType",sortType );
				k.put("value", "END");
			}
			break;	
		}
		return k;
	}
	
	public boolean IsString(String word) {
	    String regEx = "\"\\w+\"";
	    // 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx);
	    Matcher m = pattern.matcher(word);
	    if(m.find()) {
	    	return true;
	    }else return false;
	    
	}
	
	/**
	 * 在判断首字母已是字符的基础上，判断是否为合法标识符
	 * 
	 * */
	public boolean IsRightId(String word){
		char c;
		for(int j = 1; j<word.length();j++) {
			c = word.charAt(j);
			if (GetTypes(c) =="letter") {
				continue;
			}
			else if(GetTypes(c) =="digit") {
				continue;
			}else return false;
		}
		return true;
	}
	
	/**
	 * 在判断首字符为数字的情况下，判断是否为合法数字
	 * */
	public boolean IsNumber(String word) {
		char c;
		for(int j =1;j<word.length();j++) {
			c = word.charAt(j);
			if(GetTypes(c) == "digit") continue;
			else return false;
		}
		return true;
	}
	
	
	/**
	 * 判断单运算符
	 *
	 * */
	
	public boolean IsOperator(char c) {
	   char opts[] = { '+', '-', '*', '/', '=', '>', '<', '&', '|',
		'!' ,'(',')',',',';','{','}'};
		for(int j = 0;j<opts.length;j++) {
			if(c == opts[j]) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 判断双运算符
	 * 
	 * */
	public boolean IsDoubleOperator(String c) {
		   String opts[] = { "**","<=","&&",">=","!=","++" };
		   
			for(int j = 0;j<opts.length;j++) {
				if(c.equals(opts[j])) {
					return true;
				}
			}
			return false;
		}
}
