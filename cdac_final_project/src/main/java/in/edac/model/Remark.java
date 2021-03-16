package in.edac.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the remark database table.
 * 
 */
@Entity
@NamedQuery(name="Remark.findAll", query="SELECT r FROM Remark r")
public class Remark implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="remark_id")
	private int remarkId;

	private String remark;

	//bi-directional many-to-one association to Feedback
	@OneToMany(mappedBy="remark1")
	private List<Feedback> feedbacks1;

	//bi-directional many-to-one association to Feedback
	@OneToMany(mappedBy="remark2")
	private List<Feedback> feedbacks2;

	//bi-directional many-to-one association to Feedback
	@OneToMany(mappedBy="remark3")
	private List<Feedback> feedbacks3;

	public Remark() {
	}

	public int getRemarkId() {
		return this.remarkId;
	}

	public void setRemarkId(int remarkId) {
		this.remarkId = remarkId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Feedback> getFeedbacks1() {
		return this.feedbacks1;
	}

	public void setFeedbacks1(List<Feedback> feedbacks1) {
		this.feedbacks1 = feedbacks1;
	}

	public Feedback addFeedbacks1(Feedback feedbacks1) {
		getFeedbacks1().add(feedbacks1);
		feedbacks1.setRemark1(this);

		return feedbacks1;
	}

	public Feedback removeFeedbacks1(Feedback feedbacks1) {
		getFeedbacks1().remove(feedbacks1);
		feedbacks1.setRemark1(null);

		return feedbacks1;
	}

	public List<Feedback> getFeedbacks2() {
		return this.feedbacks2;
	}

	public void setFeedbacks2(List<Feedback> feedbacks2) {
		this.feedbacks2 = feedbacks2;
	}

	public Feedback addFeedbacks2(Feedback feedbacks2) {
		getFeedbacks2().add(feedbacks2);
		feedbacks2.setRemark2(this);

		return feedbacks2;
	}

	public Feedback removeFeedbacks2(Feedback feedbacks2) {
		getFeedbacks2().remove(feedbacks2);
		feedbacks2.setRemark2(null);

		return feedbacks2;
	}

	public List<Feedback> getFeedbacks3() {
		return this.feedbacks3;
	}

	public void setFeedbacks3(List<Feedback> feedbacks3) {
		this.feedbacks3 = feedbacks3;
	}

	public Feedback addFeedbacks3(Feedback feedbacks3) {
		getFeedbacks3().add(feedbacks3);
		feedbacks3.setRemark3(this);

		return feedbacks3;
	}

	public Feedback removeFeedbacks3(Feedback feedbacks3) {
		getFeedbacks3().remove(feedbacks3);
		feedbacks3.setRemark3(null);

		return feedbacks3;
	}

}