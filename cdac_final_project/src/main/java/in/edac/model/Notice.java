package in.edac.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the notice database table.
 * 
 */
@Entity
@NamedQuery(name="Notice.findAll", query="SELECT n FROM Notice n")
public class Notice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="notice_id")
	private int noticeId;

	@Column(name="notice_desc")
	private String noticeDesc;

	@Column(name="notice_title")
	private String noticeTitle;

	//bi-directional many-to-one association to Batch
	@ManyToOne
	@JoinColumn(name="notice_batchid")
	private Batch batch;

	public Notice() {
	}

	public int getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeDesc() {
		return this.noticeDesc;
	}

	public void setNoticeDesc(String noticeDesc) {
		this.noticeDesc = noticeDesc;
	}

	public String getNoticeTitle() {
		return this.noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public Batch getBatch() {
		return this.batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

}