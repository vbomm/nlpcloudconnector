package com.github.vbomm.nlpcloudconnector;

public class Parameter {

    private String name;
    private String value;

    public Parameter(String name, String value) {
        this.name = name;
        this.value = "\"" + value + "\"";
    }

    public Parameter(String name, int value) {
        this.name = name;
        this.value = Integer.toString(value);
    }

    public Parameter(String name, boolean value) {
        this.name = name;
        this.value = value ? "True" : "False";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
