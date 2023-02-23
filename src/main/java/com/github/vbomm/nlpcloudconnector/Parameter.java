package com.github.vbomm.nlpcloudconnector;

public class Parameter {

    private String name;
    private String value;
    private boolean isNumber;

    private Parameter(String name, String value, boolean isNumber) {
        this.name = name;
        this.value = value;
        this.isNumber = isNumber;
    }

    public Parameter(String name, String value) {
        this(name, value, false);
    }

    public Parameter(String name, int value) {
        this(name, Integer.toString(value), true);
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

    public boolean getIsNumber() {
        return isNumber;
    }

    public void setIsNumber(boolean isNumber) {
        this.isNumber = isNumber;
    }
}
