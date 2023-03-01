package com.github.vbomm.nlpcloudconnector;

import com.github.vbomm.nlpcloudconnector.Generation.Generation;
import com.github.vbomm.nlpcloudconnector.Generation.GenerationBuilder;
import com.github.vbomm.nlpcloudconnector.Question.Question;
import com.github.vbomm.nlpcloudconnector.Question.QuestionBuilder;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        Dotenv config = Dotenv.configure().load();
        String token = config.get("NLPCLOUDTOKEN");

        //NlpCloud nlp = new NlpCloud(token);
        Session session = new Session(token);

        // example question
        try {
            String question = "How many threats are blocked per day?";
            String context = "We have built security into every component of the network. Cloudflare’s 155 Tbps network blocks an average of 70 billion threats per day, including some of the largest DDoS attacks ever recorded. Each and every login, request, and response that goes through our network strengthens the machine learning that we apply to detect and block threats at the edge, before they ever reach your organization.";

            Question example = new QuestionBuilder(Model.robertaBaseSquad2(), question, context).build();
            String answer = session.request(example);

            System.out.println(answer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // example generation
        try {
            String text = "GPT is a powerful NLP model ";

            Generation example = new GenerationBuilder(Model.gptJ(), text).gpu(true).max_length(64).build();
            String generated_text = session.request(example);

            System.out.println(generated_text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}