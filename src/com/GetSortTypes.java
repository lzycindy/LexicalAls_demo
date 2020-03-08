package com;

public class GetSortTypes {
	public int getTypes(String word) {
		int sortType = 0;
		switch(word) {
		case "DIM":
			sortType = 1;
			break;
		case "IF":
			sortType = 2;
			break;
		case "DO":
			sortType = 3;
			break;
		case "STOP":
			sortType = 4;
			break;
		case "END":
			sortType = 5;
			break;
		case "ID":
			sortType= 6;
			break;
		case "NUM":
			sortType= 7;
			break;
		case "=":
			sortType = 8;
			break;
		case "+":
			sortType = 9;
			break;
		case "*":
			sortType = 10;
			break;
		case "**":
			sortType = 11;
			break;
		case ",":
			sortType = 12;
			break;
		case ">=":
			sortType = 13;
			break;
		case "<=":
			sortType = 14;
			break;
		case "(":
			sortType = 15;
			break;
		case ")":
			sortType = 16;
			break;
		case "/":
			sortType = 17;
			break;
		case "-":
			sortType = 18;
			break;
		case "|":
			sortType = 19;
			break;
		case "!=":
			sortType = 20;
			break;
		case "&&":
			sortType = 21;
			break;
		case "<":
			sortType = 22;
			break;
		case ">":
			sortType = 23;
			break;
		case ";":
			sortType = 24;
			break;
		case "STATIC":
			sortType = 25;
			break;
		case "DEFAULT":
			sortType = 26;
			break;
		case "String":
			sortType = 27;
			break;
		case "{":
			sortType = 28;
			break;
		case "}":
			sortType = 29;
			break;
		case "++":
			sortType = 30;
			break;
				
		default:
			sortType = -1;
			break;
		}
		return sortType;
	}
}
