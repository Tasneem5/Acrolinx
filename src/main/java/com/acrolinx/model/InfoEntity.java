package com.acrolinx.model;

public class InfoEntity {
	private int words;
	private String time;

	public InfoEntity() {

	}

	public InfoEntity(int words, String time) {

		this.words = words;
		this.time = time;
	}

	public int getWords() {
		return words;
	}

	public void setWords(int words) {
		this.words = words;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
