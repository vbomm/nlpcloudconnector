package com.github.vbomm.nlpcloudconnector.Question;

public class QuestionBuilder {

    private String model;
    private boolean gpu;
    private String question;
    private String context;

    public QuestionBuilder(String model, String question, String context) {
        this.model = model;
        this.question = question;
        this.context = context;

        gpu = false;
    }


    public QuestionBuilder gpu(boolean gpu) {
        this.gpu = gpu;

        return this;
    }

    public Question build() {
        return new Question(model, gpu, question, context);
    }
}