package in.edac.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="stud_id")
	private long studId;

	@Column(name="stud_city")
	private String studCity;

	@Temporal(TemporalType.DATE)
	@Column(name="stud_dob")
	private Date studDob;

	@Column(name="stud_email")
	private String studEmail;

	@Column(name="stud_fname")
	private String studFname;

	@Column(name="stud_lname")
	private String studLname;

	@Column(name="stud_mname")
	private String studMname;

	@Column(name="stud_mobile")
	private int studMobile;

	@Column(name="stud_rank")
	private int studRank;

	//bi-directional many-to-one association to ModuleResult
	@OneToMany(mappedBy="student")
	
	private List<ModuleResult> moduleResults;

	//bi-directional one-to-one association to User
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="stud_id")
	private User user;

	//bi-directional many-to-one association to Batch
	@ManyToOne
	
	@JoinColumn(name="stud_batchid")
	private Batch batch;

	//bi-directional many-to-one association to State
	@ManyToOne
	
	@JoinColumn(name="stud_state")
	private State state;

	//bi-directional many-to-one association to Country
	@ManyToOne
	
	@JoinColumn(name="stud_country")
	private Country country;

	//bi-directional many-to-one association to Gender
	@ManyToOne
	
	@JoinColumn(name="stud_gender")
	private Gender gender;

	//bi-directional many-to-one association to Center
	@ManyToOne
	
	@JoinColumn(name="stud_center")
	private Center center;

	public Student() {
	}

	public long getStudId() {
		return this.studId;
	}

	public void setStudId(long studId) {
		this.studId = studId;
	}

	public String getStudCity() {
		return this.studCity;
	}

	public void setStudCity(String studCity) {
		this.studCity = studCity;
	}

	public Date getStudDob() {
		return this.studDob;
	}

	public void setStudDob(Date studDob) {
		this.studDob = studDob;
	}

	public String getStudEmail() {
		return this.studEmail;
	}

	public void setStudEmail(String studEmail) {
		this.studEmail = studEmail;
	}

	public String getStudFname() {
		return this.studFname;
	}

	public void setStudFname(String studFname) {
		this.studFname = studFname;
	}

	public String getStudLname() {
		return this.studLname;
	}

	public void setStudLname(String studLname) {
		this.studLname = studLname;
	}

	public String getStudMname() {
		return this.studMname;
	}

	public void setStudMname(String studMname) {
		this.studMname = studMname;
	}

	public int getStudMobile() {
		return this.studMobile;
	}

	public void setStudMobile(int studMobile) {
		this.studMobile = studMobile;
	}

	public int getStudRank() {
		return this.studRank;
	}

	public void setStudRank(int studRank) {
		this.studRank = studRank;
	}

	public List<ModuleResult> getModuleResults() {
		return this.moduleResults;
	}

	public void setModuleResults(List<ModuleResult> moduleResults) {
		this.moduleResults = moduleResults;
	}

	public ModuleResult addModuleResult(ModuleResult moduleResult) {
		getModuleResults().add(moduleResult);
		moduleResult.setStudent(this);

		return moduleResult;
	}

	public ModuleResult removeModuleResult(ModuleResult moduleResult) {
		getModuleResults().remove(moduleResult);
		moduleResult.setStudent(null);

		return moduleResult;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Batch getBatch() {
		return this.batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Center getCenter() {
		return this.center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}

}