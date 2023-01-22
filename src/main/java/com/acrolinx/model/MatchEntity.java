package com.acrolinx.model;

import java.util.List;

public class MatchEntity {
	private String surface;
	private String beginOffset;
	private String endOffset;
	private List<String> replacement;

	public MatchEntity() {
		super();
	}

	public MatchEntity(String surface, String beginOffset, String endOffset, List<String> replacement) {
		super();
		this.surface = surface;
		this.beginOffset = beginOffset;
		this.endOffset = endOffset;
		this.replacement = replacement;
	}

	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public String getBeginOffset() {
		return beginOffset;
	}

	public void setBeginOffset(String beginOffset) {
		this.beginOffset = beginOffset;
	}

	public String getEndOffset() {
		return endOffset;
	}

	public void setEndOffset(String endOffset) {
		this.endOffset = endOffset;
	}

	public List<String> getReplacement() {
		return replacement;
	}

	public void setReplacement(List<String> replacement) {
		this.replacement = replacement;
	}

}
