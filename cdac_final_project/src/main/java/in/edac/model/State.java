package in.edac.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the state database table.
 * 
 */
@Entity
@NamedQuery(name="State.findAll", query="SELECT s FROM State s")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="state_id")
	private int stateId;

	@Column(name="state_name")
	private String stateName;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="country_id")
	private Country country;

	//bi-directional many-to-one association to Student
	@JsonIgnore
	@OneToMany(mappedBy="state")
	private List<Student> students;

	public State() {
	}

	public int getStateId() {
		return this.stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setState(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setState(null);

		return student;
	}

}