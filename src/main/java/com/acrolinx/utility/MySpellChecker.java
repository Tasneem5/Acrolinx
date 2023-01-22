package com.acrolinx.utility;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.acrolinx.model.IssueEntity;
import com.acrolinx.model.MatchEntity;
import com.swabunga.spell.engine.Word;
import com.swabunga.spell.event.SpellChecker;
import com.swabunga.spell.event.StringWordTokenizer;
import com.swabunga.spell.event.TeXWordFinder;

public class MySpellChecker {
	private SpellChecker spellChecker;
	private List<String> misspelledWords = new ArrayList<String>();;
	private MyDictionaryMap dictionaryMap;

	public MySpellChecker(String filePath) {
		if (filePath != null && !filePath.isEmpty()) {
			dictionaryMap = MyDictionaryMap.getInstance(filePath);
			spellChecker = new SpellChecker(dictionaryMap.getDictionaryMap());
			MySpellCheckListner listener = new MySpellCheckListner(misspelledWords);
			spellChecker.addSpellCheckListener(listener);
		}
	}

	// Method to Detect Misspelled Words From Given Line
	public List<String> detectMisspelledWords(String text) {
		StringWordTokenizer strTokenizer = new StringWordTokenizer(text, new TeXWordFinder());
		spellChecker.checkSpelling(strTokenizer);
		return misspelledWords;
	}

	// Method to do Correction on Misspelled Words for the given Line
	public String doCorrection(String line, List<IssueEntity> listIssues) {
		List<String> misSpelledWords = detectMisspelledWords(line);

		for (String misSpelledWord : misSpelledWords) {
			List<String> suggestions = getSuggestions(misSpelledWord);
			if (suggestions.size() == 0)
				continue;
			String bestSuggestion = suggestions.get(0);
			IssueEntity issueEntity = new IssueEntity();
			MatchEntity match = new MatchEntity();
			match.setSurface(misSpelledWord);
			match.setReplacement(suggestions);
			getOffset(match, misSpelledWord, line);
			line = line.replace(misSpelledWord, bestSuggestion);
			issueEntity.setMatch(match);
			issueEntity.setType("Spelling");
			listIssues.add(issueEntity);
		}
		return line;
	}

	// Getting Suggestions for Misspelled Words
	public List<String> getSuggestions(String misspelledWord) {
		@SuppressWarnings("unchecked")
		List<Word> words = spellChecker.getSuggestions(misspelledWord, 0);
		List<String> suggestions = new ArrayList<String>();
		for (Word suggestion : words) {
			suggestions.add(suggestion.getWord());
		}
		return suggestions;
	}

	// Fetch the index of all the occurrence of incorrect word
	public void getOffset(MatchEntity match, String misSpelledWord, String line) {
		int count = StringUtils.countMatches(line, misSpelledWord);
		int index = line.indexOf(misSpelledWord);
		String beginOff = String.valueOf(index);
		String endOff = String.valueOf(index + misSpelledWord.length());
		if (count > 1) {
			for (int j = 0; j < count; j++) {
				if (j == 0) {
					// Do nothing
				} else {
					beginOff += "," + line.indexOf(misSpelledWord, index);
					endOff += "," + (line.indexOf(misSpelledWord, index) + misSpelledWord.length());
				}
				index = index + misSpelledWord.length();
			}
		}
		match.setBeginOffset(beginOff);
		match.setEndOffset(endOff);
	}
}
