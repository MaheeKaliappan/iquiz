package com.itechies.iquiz.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.itechies.iquiz.model.QuestionAndAnswer;

public class FetchQuestionPaperRowMapper implements ResultSetExtractor<List<QuestionAndAnswer>> {

	@Override
	public List<QuestionAndAnswer> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<QuestionAndAnswer> list = new ArrayList<QuestionAndAnswer>();
		while (rs.next()) {

			QuestionAndAnswer questionAndAnswer = new QuestionAndAnswer();
			questionAndAnswer.setQuestion_id(rs.getInt("questions.id"));
			questionAndAnswer.setQuestion(rs.getString("questions.question"));
			questionAndAnswer.setChoice(rs.getString("choice.choice"));
			questionAndAnswer.setAns_id(rs.getInt("choice.id"));
			list.add(questionAndAnswer);
		}
		return list;
	}

}
