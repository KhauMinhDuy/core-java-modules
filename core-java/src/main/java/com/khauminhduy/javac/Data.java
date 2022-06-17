package com.khauminhduy.javac;

import java.util.ArrayList;
import java.util.List;

public class Data {

	private List<String> textList = new ArrayList<>();
	
	public void addText(String text) {
		this.textList.add(text);
	}
	
	public List<String> getTextList() {
		return this.textList;
	}
	
}
