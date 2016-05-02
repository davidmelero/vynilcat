package com.vynilcat.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="search")
@Table(name="search")
public class Search {

	@Id
	@GeneratedValue
	@Column(name="idSearch")
	private Integer id;
	
	@NotNull
	@Size(min=1, message="La búsqueda está vacia")
	@Column(name="searched")
	private String searched;

	public Search(String searched){
		this(null,searched);
	}
	
	public Search(Integer id, String searched){
		this.id = id;
		this.searched = searched;
	}
	
	public Search() {
		
	}

	public String getSearched() {
		return searched;
	}

	public void setSearched(String searched) {
		this.searched = searched;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
