package in.edac.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the rank database table.
 * 
 */
@Entity
@NamedQuery(name="Rank.findAll", query="SELECT r FROM Rank r")
public class Rank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="rank_id")
	private int rankId;

	private int rank;

	//bi-directional many-to-one association to ModuleResult
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="result_id")
	private ModuleResult moduleResult;

	public Rank() {
	}

	public int getRankId() {
		return this.rankId;
	}

	public void setRankId(int rankId) {
		this.rankId = rankId;
	}

	public int getRank() {
		return this.rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public ModuleResult getModuleResult() {
		return this.moduleResult;
	}

	public void setModuleResult(ModuleResult moduleResult) {
		this.moduleResult = moduleResult;
	}

}