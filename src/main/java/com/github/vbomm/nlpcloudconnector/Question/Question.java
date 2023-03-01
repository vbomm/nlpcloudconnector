package com.github.vbomm.nlpcloudconnector.Question;

import com.github.vbomm.nlpcloudconnector.Session;

public class Question {

    private String model;
    private boolean gpu;
    private String question;
    private String context;

    public Question(String model, boolean gpu, String question, String context) {
        this.model = model;
        this.gpu = gpu;
        this.question = question;
        this.context = context;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
