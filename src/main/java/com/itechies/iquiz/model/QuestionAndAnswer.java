package com.itechies.iquiz.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuestionAndAnswer {

	// fetch record
	private String question;
	private Integer question_id;
	private Integer ans_id;
	private Integer correct_ans_id;
	private String choice;
	private Boolean status;

	// crct the record
	private List<String> choices;
	private List<Integer> choices_ids;
	private String correctAnswer;


	public Integer getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}

	

	public Integer getAns_id() {
		return ans_id;
	}

	public List<Integer> getChoices_ids() {
		return choices_ids;
	}

	public void setChoices_ids(List<Integer> choices_ids) {
		this.choices_ids = choices_ids;
	}

	public void setAns_id(Integer ans_id) {
		this.ans_id = ans_id;
	}



	public List<Integer> getQuestion_ids() {
		return choices_ids;
	}

	public void setQuestion_ids(List<Integer> question_ids) {
		this.choices_ids = question_ids;
	}

	public Integer getCorrect_ans_id() {
		return correct_ans_id;
	}

	public void setCorrect_ans_id(Integer correct_ans_id) {
		this.correct_ans_id = correct_ans_id;
	}

	
	public List<String> getChoices() {
		return choices;
	}

	public void setChoices(List<String> choices) {
		this.choices = choices;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
