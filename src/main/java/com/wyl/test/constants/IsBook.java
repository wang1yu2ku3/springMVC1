package com.wyl.test.constants;

public enum IsBook {
	YES(1,"是"),
	NO(2,"否");
	
	private String name;
	private int value;
	
	private IsBook(int value, String name){
        this.value = value;
        this.name = name;
    }
	
	public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
