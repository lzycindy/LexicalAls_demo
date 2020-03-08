package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analysis {
	// �������ͱ�
	GetSortTypes t = new GetSortTypes();
	
	/**
	 * �ж�list�Ƿ�Ϊ�Ϸ��Ӵ�
	 * @param list
	 * @return
	 * */
	public List<Map> GetSortTypeAndValue(String[] list){
		
		String sortType = "",value = "";
		char fc; //���ַ�
		String fct,word; //fct ��һ������ĸ���ͣ�word ��Ҫ�жϵ���
		List<Map> rlist = new ArrayList<Map>();
		int l = list.length;
		
		
		for(int i=0;i<l;i++) {
			Map<String,String>map=new HashMap<String,String>();//map�����жϳɹ����ֺ��ֱ���
			word = list[i];
			//�пգ����л��߳��մ�)
			if(word == ""|| word == null) continue;
			
			fc = word.charAt(0);
			fct = GetTypes(fc);
			
		// �жϹؼ��ֺͱ�ʶ��
			if(fct == "letter") {
				
				//�ж��Ƿ����Ϊ�ؼ���
				if(fc == 'D'|| fc == 'I' || fc == 'S' || fc == 'E') {
					Map<String,String>m=new HashMap<String,String>();
					m = GetKeyWords(word);
					if(m != null) {
						sortType = m.get("sortType");
						value = word;
					}else {
						
						//�ж��Ƿ�Ϊ��׼�ı�ʶ��ID
						if(IsRightId(word)) {
							sortType = ""+t.getTypes("ID");
							value =  word;
						}
						else {
							System.out.println("�÷���1"+ word+"���Ϸ�");
							
						}
					}
				}else {
					// ���ַ�Ϊ�����ַ�
					if(IsRightId(word)) {
						sortType = ""+t.getTypes("ID");
						value =  word;
					}else {
						System.out.println("�÷���2"+ word+"���Ϸ�");
						
					}
				}
			
			}
			//�ж��ַ���
			if(IsString(word)) {
				sortType = "0";
				value = word;
			}
			
			//�ж�����
			if(fct == "digit") {
				if(IsNumber(word)) {
					sortType = ""+t.getTypes("NUM");
					value =  word;
				}
			}
			
			//�жϷ���
			if(fct == "opts") {
				int len = word.length();
				
				//�жϵ�����
				if(len == 1) {
					if(IsOperator(word.charAt(0))) {
						sortType = ""+t.getTypes(word);
						value =  word;
					}
					else {
						System.out.println("�÷���3"+ word+"���Ϸ�");
					}
				}else if(len == 2) {
					if(IsDoubleOperator(word)) {
						sortType = ""+t.getTypes(word);
						value =  word;
					}
					else {
						System.out.println("�÷���4"+ word+"���Ϸ�");
					}
				}
				//�ж��ַ���
				else if(IsString(word)) {
					sortType = "0";
					value = word;
				}
				else {
					System.out.println("�÷���5"+ word+"���Ϸ�");
				}
			}
			
			// �п�
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
	 * �����ַ����ж�����
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
	 * �ж��Ƿ�ؼ���
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
	    // ����������ʽ
	    Pattern pattern = Pattern.compile(regEx);
	    Matcher m = pattern.matcher(word);
	    if(m.find()) {
	    	return true;
	    }else return false;
	    
	}
	
	/**
	 * ���ж�����ĸ�����ַ��Ļ����ϣ��ж��Ƿ�Ϊ�Ϸ���ʶ��
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
	 * ���ж����ַ�Ϊ���ֵ�����£��ж��Ƿ�Ϊ�Ϸ�����
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
	 * �жϵ������
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
	 * �ж�˫�����
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
