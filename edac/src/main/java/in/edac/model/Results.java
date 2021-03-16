package in.edac.model;

import javax.persistence.Entity;

import javax.persistence.Id;

@Entity
public class Results {

	@Id
	private long id;
	private String name;
	private String marks;
	
	public Results() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Results(String name, String marks) {
		super();
		this.name = name;
		this.marks = marks;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "Results [id="+id+", name="+name+", marks"+marks+"]";
	}
}
