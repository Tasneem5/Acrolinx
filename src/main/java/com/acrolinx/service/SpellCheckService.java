package com.acrolinx.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.acrolinx.model.CheckResponseEntity;
import com.acrolinx.model.InfoEntity;
import com.acrolinx.model.IssueEntity;
import com.acrolinx.model.RequestText;
import com.acrolinx.utility.MySpellChecker;

@Service
public class SpellCheckService {
	private String DICTIONARY = "../eclipse-workspace-acrolinx-test/spellCorrection/src/dictionary/dictionary.txt";

	public CheckResponseEntity getResponseBody(RequestText text) {
		CheckResponseEntity checkResponseEntity = new CheckResponseEntity();
		MySpellChecker spellChecker = new MySpellChecker(DICTIONARY);
		List<IssueEntity> listIssues = new ArrayList<IssueEntity>();
		// Spellchecker will check the input text against the dictionary present at
		// location -> DICTIONARY .Also fill the issues list .
		String correctOutputText = spellChecker.doCorrection(text.getInputText(), listIssues);

		// Setting values to output json object
		checkResponseEntity.setIssues(listIssues);

		if (listIssues.isEmpty()) {
			checkResponseEntity.setOutputText("No suggestions.");
		} else {
			checkResponseEntity.setOutputText(correctOutputText);
		}
		InfoEntity infEntity = new InfoEntity();
		ArrayList<String> list = new ArrayList<>(Arrays.asList(text.getInputText().split(" ")));
		infEntity.setWords(list.size());
		Timestamp time = new Timestamp(System.currentTimeMillis());
		infEntity.setTime(time.toString());
		checkResponseEntity.setId(UUID.randomUUID().toString());
		checkResponseEntity.setInfo(infEntity);

		return checkResponseEntity;
	}

}
