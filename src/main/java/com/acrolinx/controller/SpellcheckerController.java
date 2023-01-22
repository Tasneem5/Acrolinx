package com.acrolinx.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acrolinx.model.CheckResponseEntity;
import com.acrolinx.model.RequestText;
import com.acrolinx.service.SpellCheckService;

@RestController
@RequestMapping("/check")
public class SpellcheckerController {

	@Autowired
	SpellCheckService spellCheckService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> checkText(RequestText reqText) throws IOException {
		CheckResponseEntity checkResponseEntity = spellCheckService.getResponseBody(reqText);
		return ResponseEntity.ok(checkResponseEntity);
	}
}
