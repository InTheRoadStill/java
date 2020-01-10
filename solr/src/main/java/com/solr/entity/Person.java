package com.solr.entity;

import org.apache.solr.client.solrj.beans.Field;

public class Person {
	@Field(value = "id")
	private String id;

	@Field(value = "name")
	private String name;

	@Field(value = "description")
	private String description;
	
	@Field(value = "cheng")
	private String cheng;

	public String getId() {
	    return id;
	}

	public void setId(String id) {
	    this.id = id;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}

	public String getCheng() {
		return cheng;
	}

	public void setCheng(String cheng) {
		this.cheng = cheng;
	}
}
