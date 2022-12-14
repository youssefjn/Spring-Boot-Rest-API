package spring.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.rest.model.Student;
import spring.rest.service.StudentService;

@RestController

@RequestMapping("api/student")
public class StudentController {
private final StudentService studentService;
public StudentController (StudentService studentService) {
		this.studentService=studentService;
	}
	
	@GetMapping
	public List<Student> getStudents(){
		return studentService.getAllStudents();
	}
	@PostMapping (path = "registration")
	public Student addStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
	}
	
	@DeleteMapping (path = "{studentId}")
	public void deleteStudent(@PathVariable ("studentId") Long id) {
		studentService.deleteStudentById(id);
	}
	
	@PutMapping (path = "{studentId}")
	public void updateStudent(	@PathVariable ("studentId") Long id,
								@RequestParam ( required = false)  String name,
								@RequestParam (required = false ) String email){
		studentService.updateStudentById(id, name , email);
	}
}
