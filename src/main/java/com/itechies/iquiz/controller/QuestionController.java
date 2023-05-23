package com.itechies.iquiz.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.itechies.iquiz.dao.QuestionDAO;
import com.itechies.iquiz.model.Question;
import com.itechies.iquiz.model.QuestionAndAnswer;

@RestController
public class QuestionController {

	@Autowired
	QuestionDAO questionDao;

	// ADD QUESTION
	@RequestMapping(value = "/add_question", method = RequestMethod.POST)
	public String addQuestion(@ModelAttribute Question question) {
		questionDao.addQuestion(question);
		return " Added SuccessFully";
	}

	// VIEW QUESTION
	@ResponseBody
	@RequestMapping(value = "/view_questions", method = RequestMethod.GET)
	public Map<String, Object> viewQuestions() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("questions", questionDao.fetchAllQuestions());
		return map;

	}

	// DELETE QUESTION
	@RequestMapping(value = "/delete_question/{id}", method = RequestMethod.DELETE)
	public String deleteQuestion(@PathVariable Integer id) {
		questionDao.deleteQuestion(id);
		return "Deleted SuccessFully";

	}

	// FETCH ONE QUESTION RECORD
	@ResponseBody
	@RequestMapping(value = "/fetch_question/{id}", method = RequestMethod.GET)
	public Map<String, Question> fetchQuestion(@PathVariable Integer id) {
		Question question = questionDao.fetchQuestionDetail(id);
		Map<String, Question> map = new HashMap<String, Question>();
		map.put("question", question);
		return map;
	}

	// UPDATE QUESTION
	@RequestMapping(value = "/update_question/{id}", method = RequestMethod.POST)
	public String updateQuestion(@PathVariable Integer id, @ModelAttribute Question question) {
		// www-for-urlencoded la value pass pannanum
		questionDao.updateQuestion(id, question);
		return "Updated Successfully";
	}

	@RequestMapping(value = "/fetch_question_answer/{exam_id}", method = RequestMethod.POST)
	public List<QuestionAndAnswer> fetchQuestionAndAnswer(@PathVariable Integer exam_id) {
		List<QuestionAndAnswer> questionAndAnswer = questionDao.fetchQuestionAndAnswer(exam_id);
		questionAndAnswer = organizeTheData(questionAndAnswer);
		return questionAndAnswer;
	}

	private List<QuestionAndAnswer> organizeTheData(List<QuestionAndAnswer> questionAndAnswer) {
		List<QuestionAndAnswer> list = new ArrayList<QuestionAndAnswer>();
		for (int i = 0; i < questionAndAnswer.size(); i++) {
			int start = i;
			QuestionAndAnswer questionAndAnswer2 = new QuestionAndAnswer();
			questionAndAnswer2.setQuestion(questionAndAnswer.get(i).getQuestion());
			questionAndAnswer2.setQuestion_id(questionAndAnswer.get(i).getQuestion_id());
			List<String> choice = new ArrayList<String>();
			List<Integer>ids= new ArrayList<Integer>();
			
			
			for (int j = start; j < start + 2; j++) {
				choice.add(questionAndAnswer.get(j).getChoice());
				ids.add(questionAndAnswer.get(j).getAns_id());
				
				
				if (questionAndAnswer.get(j).getStatus()) {
					
					questionAndAnswer2.setCorrectAnswer(questionAndAnswer.get(j).getChoice());
					questionAndAnswer2.setCorrect_ans_id(questionAndAnswer.get(j).getAns_id());
				}
				
			}
			questionAndAnswer2.setChoices(choice);
			questionAndAnswer2.setChoices_ids(ids);
			
			
			i += 1;
			list.add(questionAndAnswer2);

		}
		return list;
	}

	@RequestMapping(value = "/suffle_question_answer_update_student_question_table/{exam_id}/{student_id}", method = RequestMethod.POST)
	public String suffleQuestionAndAnswer(@PathVariable Integer exam_id ,@PathVariable Integer student_id) {
		
		List<QuestionAndAnswer> questionAndAnswer = fetchQuestionAndAnswer(exam_id);
		List<Integer> questionShuffle = new ArrayList<Integer>();
		List<Integer> answerShuffle = new ArrayList<Integer>();
		for (int i = 0; i < questionAndAnswer.size(); i++)
			questionShuffle.add(i);
		for (int i = 0; i < questionAndAnswer.get(i).getChoices().size(); i++)
			answerShuffle.add(i);
		Collections.shuffle(questionShuffle);
		Collections.shuffle(answerShuffle);
		List<QuestionAndAnswer> shuffleQuestionAnswer = new ArrayList<QuestionAndAnswer>();
		for (int i = 0; i < questionAndAnswer.size(); i++) {
			QuestionAndAnswer questionAndAnswer2 = new QuestionAndAnswer();
			questionAndAnswer2.setQuestion(questionAndAnswer.get(questionShuffle.get(i)).getQuestion());
			questionAndAnswer2.setCorrectAnswer(questionAndAnswer.get(questionShuffle.get(i)).getCorrectAnswer());
			List<String> choice = new ArrayList<String>();
			List<Integer>ids= new ArrayList<Integer>();
			for (int j = 0; j < 2; j++) {
				choice.add(questionAndAnswer.get(questionShuffle.get(i)).getChoices().get(answerShuffle.get(j)));
				ids.add(questionAndAnswer.get(questionShuffle.get(i)).getChoices_ids().get(j));
				
			
			}
			questionAndAnswer2.setQuestion_id(questionAndAnswer.get(questionShuffle.get(i)).getQuestion_id());
			questionAndAnswer2.setCorrect_ans_id(questionAndAnswer.get(questionShuffle.get(i)).getCorrect_ans_id());
			questionAndAnswer2.setChoices(choice);
			questionAndAnswer2.setChoices_ids(ids);
			
			shuffleQuestionAnswer.add(questionAndAnswer2);

		}
		
		questionDao.updateStudentTable(shuffleQuestionAnswer,exam_id,student_id);
		return "updated successfully";
	}
	
	@RequestMapping(value = "/show_student_question/{exam_id}/{student_id}", method = RequestMethod.POST)
	public List<QuestionAndAnswer> showStudentQuestion(@PathVariable Integer exam_id,@PathVariable Integer student_id) {
	List<QuestionAndAnswer> questionAndAnswer = questionDao.showStudentQuestion(exam_id,student_id);
	System.out.println(questionAndAnswer.size());
	for(int i=0;i<6;i++)
	{
		int start=i;
		System.out.println(questionAndAnswer.get(i).getQuestion());
		for(int j=i;j<=start+1;j++)
		{
			System.out.println(questionAndAnswer.get(j).getChoice()+" ");
		}i++;
	}
		
		return questionAndAnswer;
	}
	@RequestMapping(value = "/submit_answer/{exam_id}/{student_id}", method = RequestMethod.POST)
	public String submitAnswer(@PathVariable Integer exam_id,@PathVariable Integer student_id, @ModelAttribute QuestionAndAnswer
			questionAnswer) {
	questionDao.sumbitAnswer(exam_id,student_id,questionAnswer);
	return "updated";
	}
	@RequestMapping(value = "/view_mark/{exam_id}/{student_id}", method = RequestMethod.POST)
	public List<Integer> viewMark(@PathVariable Integer exam_id,@PathVariable Integer student_id) {
	
	return questionDao.viewMark(exam_id,student_id);
	}
}
