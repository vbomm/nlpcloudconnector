package com.github.vbomm.nlpcloudconnector.Generation;

public class Generation {
    private String model;
    private boolean gpu;
    private String text;
    private int max_length;

    public Generation(String model, boolean gpu, String text, int max_length) {
        this.model = model;
        this.gpu = gpu;
        this.text = text;
        this.max_length = max_length;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isGpu() {
        return gpu;
    }

    public void setGpu(boolean gpu) {
        this.gpu = gpu;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMax_length() {
        return max_length;
    }

    public void setMax_length(int max_length) {
        this.max_length = max_length;
    }
}
