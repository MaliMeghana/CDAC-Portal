package in.edac.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the batch database table.
 * 
 */
@Entity
@NamedQuery(name="Batch.findAll", query="SELECT b FROM Batch b")
public class Batch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="batch_id")
	private int batchId;

	@Column(name="batch_month")
	private String batchMonth;

	@Column(name="batch_year")
	private int batchYear;
	
	//bi-directional many-to-one association to ModuleResult
		@OneToMany(mappedBy="batch")
		private List<ModuleResult> moduleResults;

	//bi-directional many-to-one association to Notice
	@JsonIgnore
	@OneToMany(mappedBy="batch",cascade = CascadeType.ALL)
	private List<Notice> notices;

	
	//bi-directional many-to-one association to Student
	@JsonIgnore
	@OneToMany(mappedBy="batch", cascade = CascadeType.ALL)
	private List<Student> students;

	public Batch() {
	}

	public int getBatchId() {
		return this.batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getBatchMonth() {
		return this.batchMonth;
	}

	public void setBatchMonth(String batchMonth) {
		this.batchMonth = batchMonth;
	}

	public int getBatchYear() {
		return this.batchYear;
	}

	public void setBatchYear(int batchYear) {
		this.batchYear = batchYear;
	}

	public List<Notice> getNotices() {
		return this.notices;
	}

	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}

	public Notice addNotice(Notice notice) {
		getNotices().add(notice);
		notice.setBatch(this);

		return notice;
	}

	public Notice removeNotice(Notice notice) {
		getNotices().remove(notice);
		notice.setBatch(null);

		return notice;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setBatch(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setBatch(null);

		return student;
	}

}