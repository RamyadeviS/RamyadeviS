package com.chainsys.epassproject.model;

public class Reason {

	private String typesOfReasons;

	public Reason() {
		super();
	}

	public Reason(String typesOfReasons) {
		super();
		this.typesOfReasons = typesOfReasons;
	}

	public String getTypesOfReasons() {
		return typesOfReasons;
	}

	public void setTypesOfReasons(String typesOfReasons) {
		this.typesOfReasons = typesOfReasons;
	}

	@Override
	public String toString() {
		return "Reason [typesOfReasons=" + typesOfReasons + "]";
	}

}
