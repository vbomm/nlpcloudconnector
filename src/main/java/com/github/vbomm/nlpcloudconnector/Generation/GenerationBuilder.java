package com.github.vbomm.nlpcloudconnector.Generation;

import com.github.vbomm.nlpcloudconnector.Question.QuestionBuilder;

public class GenerationBuilder {
    private String model;
    private boolean gpu;
    private String text;
    private int max_length;

    public GenerationBuilder(String model, String text) {
        this.model = model;
        this.text = text;

        gpu = false;
        max_length = 50;
    }

    public GenerationBuilder gpu(boolean gpu) {
        this.gpu = gpu;

        return this;
    }

    public GenerationBuilder max_length(int max_length) {
        this.max_length = max_length;

        return this;
    }

    public Generation build() {
        return new Generation(model, gpu, text, max_length);
    }
}
