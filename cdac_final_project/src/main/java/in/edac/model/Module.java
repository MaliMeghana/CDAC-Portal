package in.edac.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the module database table.
 * 
 */
@Entity
@NamedQuery(name="Module.findAll", query="SELECT m FROM Module m")
public class Module implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="module_id")
	private int moduleId;

	@Column(name="module_name")
	private String moduleName;

	//bi-directional many-to-one association to ModuleResult
	@JsonIgnore
	@OneToMany(mappedBy="module")
	private List<ModuleResult> moduleResults;

	public Module() {
	}

	public int getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public List<ModuleResult> getModuleResults() {
		return this.moduleResults;
	}

	public void setModuleResults(List<ModuleResult> moduleResults) {
		this.moduleResults = moduleResults;
	}

	public ModuleResult addModuleResult(ModuleResult moduleResult) {
		getModuleResults().add(moduleResult);
		moduleResult.setModule(this);

		return moduleResult;
	}

	public ModuleResult removeModuleResult(ModuleResult moduleResult) {
		getModuleResults().remove(moduleResult);
		moduleResult.setModule(null);

		return moduleResult;
	}

}