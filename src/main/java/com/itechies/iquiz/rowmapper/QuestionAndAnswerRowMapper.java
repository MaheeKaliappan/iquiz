package com.itechies.iquiz.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.itechies.iquiz.model.QuestionAndAnswer;

public class QuestionAndAnswerRowMapper implements ResultSetExtractor<List<QuestionAndAnswer>> {
	

	@Override
	public List<QuestionAndAnswer> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<QuestionAndAnswer> list = new ArrayList<QuestionAndAnswer>();
		while (rs.next()) {
			QuestionAndAnswer questionAndAnswer = new QuestionAndAnswer();
			questionAndAnswer.setQuestion_id(rs.getInt("ID"));
			questionAndAnswer.setQuestion(rs.getString("question"));
			questionAndAnswer.setStatus(rs.getBoolean("status"));
			questionAndAnswer.setChoice(rs.getString("choice"));
			questionAndAnswer.setAns_id(rs.getInt("choice.id"));
			list.add(questionAndAnswer);
		}
		return list;
	}
	
	

}
/*
 * public class QuestionAndAnswerRowMapper implements
 * RowMapper<Map<String,Object> > { List<QuestionAndAnswer>list=new
 * ArrayList<QuestionAndAnswer>(); Map<String, Object>map=new HashMap<String,
 * Object>();
 * 
 * @Override public Map<String,Object> mapRow(ResultSet rs, int rowNum) throws
 * SQLException { QuestionAndAnswer questionAndAnswer = new QuestionAndAnswer();
 * questionAndAnswer.setId(rs.getInt("id"));
 * questionAndAnswer.setQuestion(rs.getString("question"));
 * questionAndAnswer.setStatus(rs.getBoolean("status"));
 * questionAndAnswer.setChoice(rs.getString("choice"));
 * System.out.println(questionAndAnswer.getQuestion());
 * System.out.println(questionAndAnswer.getChoice());
 * System.out.println(questionAndAnswer.getStatus());
 * list.add(questionAndAnswer);
 * 
 * //System.out.println(list); map.put("questions", list);
 * //System.out.println(map); return map;
 * 
 * }
 * 
 * 
 * 
 * }
 */
