package spring.rest.model;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table
public class Student {
	@Id
	@SequenceGenerator(name="student_sequence" , sequenceName = "student_sequence",allocationSize = 1)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	private Long id;
	private String name;
	private String email;
	
	@Transient
	private Integer age;
	private LocalDate dob;
	
	public Integer getAge() {
		return Period.between(this.dob,LocalDate.now()).getYears();
	}

}
