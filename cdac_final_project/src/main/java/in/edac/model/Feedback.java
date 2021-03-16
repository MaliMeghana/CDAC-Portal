package in.edac.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the feedback database table.
 * 
 */
@Entity
@NamedQuery(name="Feedback.findAll", query="SELECT f FROM Feedback f")
public class Feedback implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="feedback_id")
	private int feedbackId;

	private String comment;

	private int interaction;

	@Column(name="module_id")
	private int moduleId;

	private int overall;

	@Column(name="stud_id")
	private int studId;

	//bi-directional many-to-one association to Remark
	@ManyToOne
	@JoinColumn(name="pace")
	private Remark remark1;

	//bi-directional many-to-one association to Remark
	@ManyToOne
	@JoinColumn(name="explain")
	private Remark remark2;

	//bi-directional many-to-one association to Remark
	@ManyToOne
	@JoinColumn(name="daubt")
	private Remark remark3;

	public Feedback() {
	}

	public int getFeedbackId() {
		return this.feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getInteraction() {
		return this.interaction;
	}

	public void setInteraction(int interaction) {
		this.interaction = interaction;
	}

	public int getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public int getOverall() {
		return this.overall;
	}

	public void setOverall(int overall) {
		this.overall = overall;
	}

	public int getStudId() {
		return this.studId;
	}

	public void setStudId(int studId) {
		this.studId = studId;
	}

	public Remark getRemark1() {
		return this.remark1;
	}

	public void setRemark1(Remark remark1) {
		this.remark1 = remark1;
	}

	public Remark getRemark2() {
		return this.remark2;
	}

	public void setRemark2(Remark remark2) {
		this.remark2 = remark2;
	}

	public Remark getRemark3() {
		return this.remark3;
	}

	public void setRemark3(Remark remark3) {
		this.remark3 = remark3;
	}

}