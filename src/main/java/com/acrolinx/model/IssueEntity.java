package com.acrolinx.model;

public class IssueEntity {
	private String type;
	private MatchEntity match;

	public IssueEntity() {

	}

	public IssueEntity(String type, MatchEntity match) {

		this.type = type;
		this.match = match;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MatchEntity getMatch() {
		return match;
	}

	public void setMatch(MatchEntity match) {
		this.match = match;
	}

}
