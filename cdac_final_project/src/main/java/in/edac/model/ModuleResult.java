package in.edac.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the module_result database table.
 * 
 */
@Entity
@Table(name="module_result")
@NamedQuery(name="ModuleResult.findAll", query="SELECT m FROM ModuleResult m")
public class ModuleResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="result_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resultId;

	private int lab;

	@Column(name="max_lab")
	private int maxLab;

	@Column(name="max_mcq")
	private int maxMcq;

	@Column(name="max_total")
	private int maxTotal;

	private int mcq;

	private int total;

	//bi-directional many-to-one association to Student
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="stud_id")
	private Student student;

	//bi-directional many-to-one association to Rank
	@OneToMany(mappedBy="moduleResult")
	@JsonIgnore
	private List<Rank> ranks;

	//bi-directional many-to-one association to Module
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="module_id")
	private Module module;

	public ModuleResult() {
	}

	public int getResultId() {
		return this.resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public int getLab() {
		return this.lab;
	}

	public void setLab(int lab) {
		this.lab = lab;
	}

	public int getMaxLab() {
		return this.maxLab;
	}

	public void setMaxLab(int maxLab) {
		this.maxLab = maxLab;
	}

	public int getMaxMcq() {
		return this.maxMcq;
	}

	public void setMaxMcq(int maxMcq) {
		this.maxMcq = maxMcq;
	}

	public int getMaxTotal() {
		return this.maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public int getMcq() {
		return this.mcq;
	}

	public void setMcq(int mcq) {
		this.mcq = mcq;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Rank> getRanks() {
		return this.ranks;
	}

	public void setRanks(List<Rank> ranks) {
		this.ranks = ranks;
	}

	public Rank addRank(Rank rank) {
		getRanks().add(rank);
		rank.setModuleResult(this);

		return rank;
	}

	public Rank removeRank(Rank rank) {
		getRanks().remove(rank);
		rank.setModuleResult(null);

		return rank;
	}

	public Module getModule() {
		return this.module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}