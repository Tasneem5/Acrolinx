package com.acrolinx.model;

import java.util.List;

public class CheckResponseEntity {
	private String id;
	private InfoEntity info;
	private List<IssueEntity> issues;
	private String outputText;

	public String getOutputText() {
		return outputText;
	}

	public void setOutputText(String outputText) {
		this.outputText = outputText;
	}

	public CheckResponseEntity() {

	}

	public CheckResponseEntity(String id, InfoEntity info, List<IssueEntity> issues, String outputText) {

		this.id = id;
		this.info = info;
		this.issues = issues;
		this.outputText = outputText;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InfoEntity getInfo() {
		return info;
	}

	public void setInfo(InfoEntity info) {
		this.info = info;
	}

	public List<IssueEntity> getIssues() {
		return issues;
	}

	public void setIssues(List<IssueEntity> issues) {
		this.issues = issues;
	}

}
