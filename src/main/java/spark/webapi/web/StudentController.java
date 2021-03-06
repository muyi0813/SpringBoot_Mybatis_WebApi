package spark.webapi.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spark.webapi.entity.Student;
import spark.webapi.service.StudentService;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/student") 
public class StudentController {
	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/getbyno", method = RequestMethod.GET)
	private Map<String, Object> getStudentByNo(Integer stuNo) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 获取区域信息
		Student student = studentService.selectByPrimaryKey(stuNo);
		modelMap.put("student", student);
		return modelMap;
	}
	
	@RequestMapping(value = "/getbynopath/{no}", method = RequestMethod.GET)
	private Map<String, Object> getStudentByNoPath(@PathVariable Integer no) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 获取区域信息
		Student student = studentService.selectByPrimaryKey(no);
		modelMap.put("student", student);
		return modelMap;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	private Map<String, Object> addStudent(@RequestBody Student student)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 添加区域信息
		modelMap.put("success", studentService.insert(student));
		return modelMap;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	private Map<String, Object> modifyStudent(@RequestBody Student student)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 修改区域信息
		modelMap.put("success", studentService.updateByPrimaryKey(student));
		return modelMap;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	private Map<String, Object> removeStudent(Integer stuNo) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 修改区域信息
		modelMap.put("success", studentService.deleteByPrimaryKey(stuNo));
		return modelMap;
	}

}
