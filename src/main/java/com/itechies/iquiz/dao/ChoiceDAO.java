package com.itechies.iquiz.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.itechies.iquiz.model.Choice;
import com.itechies.iquiz.rowmapper.ChoiceRowMapper;


@Service
public class ChoiceDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	public String addChoice(Choice choice) {
		String sql="INSERT INTO choice (choice,exam_id,status,question_id) VALUES(?,?,?,?)";
		jdbcTemplate.update(sql,choice.getChoice(),choice.getExam_id(),choice.isStatus(),choice.getQuestion_id());
		return choice.getChoice();
	}

	public List<Choice> fetchAllChoices() {
		String sql = "SELECT * FROM choice";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Choice.class));
	}

	public void deleteChoice(Integer id) {
		String sql = "DELETE FROM choice WHERE ID=?";
		jdbcTemplate.update(sql, id);
		
	}

	public Choice fetchChoiceDetail(Integer id) {
		String sql = "SELECT * FROM choice WHERE ID  = ?";
		Choice choice = jdbcTemplate.queryForObject(sql, new Object[] { id }, new ChoiceRowMapper());
		return choice;
	}

	public void updateChoice(Integer id, Choice choice) {
		String sql=" UPDATE choice SET  exam_id = ?, question_id=? ,choice=?,status=? WHERE id = ?";
		jdbcTemplate.update(sql, choice.getExam_id(),choice.getQuestion_id(),choice.getChoice(),choice.isStatus(),id);
		
	}

}
