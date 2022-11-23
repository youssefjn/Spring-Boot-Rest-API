package spring.rest.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.rest.model.Student;
import spring.rest.repository.StudentRepository;


@Service
public class StudentService {
@Autowired
	private StudentRepository studentRepository;
public StudentService(StudentRepository studentRepository) {
	this.studentRepository = studentRepository;
}
	public List<Student> getAllStudents() {
	return studentRepository.findAll(); 
	}
	public Student addStudent(Student student) {
		Optional<Student>studentOptional =  studentRepository.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()){ 
			throw new IllegalStateException("email exists");
		}
	return studentRepository.save(student);
	
	}
	public void deleteStudentById(Long id) {
		if (!studentRepository.existsById(id)) {
			throw new IllegalStateException("id " + id +" doesn't exist");}
		
		studentRepository.deleteById(id);
		}
	
	
	@Transactional
	public void updateStudentById(Long id, String name, String email) {
		Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalStateException("student with id "+ id + " does not exist"));
		if ( name != null && name.length()> 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		if ( email != null && email.length()> 0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student>studentOptional =  studentRepository.findStudentByEmail(student.getEmail());
			if (studentOptional.isPresent()){ 
				throw new IllegalStateException("email exists");
			}
			student.setEmail(email);
		}
		
	}
		
		
		
	}


