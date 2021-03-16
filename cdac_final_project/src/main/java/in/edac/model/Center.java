package in.edac.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the center database table.
 * 
 */
@Entity
@NamedQuery(name="Center.findAll", query="SELECT c FROM Center c")
public class Center implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="center_id")
	private int centerId;

	private String center;

	//bi-directional many-to-one association to Student
	@JsonIgnore
	@OneToMany(mappedBy="center")
	private List<Student> students;

	public Center() {
	}

	public int getCenterId() {
		return this.centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	public String getCenter() {
		return this.center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setCenter(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setCenter(null);

		return student;
	}

}