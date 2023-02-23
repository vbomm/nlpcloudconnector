package com.github.vbomm.nlpcloudconnector;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        Dotenv config = Dotenv.configure().load();
        String token = config.get("NLPCLOUDTOKEN");

        NlpCloud nlp = new NlpCloud(token);

        // example question
        try {
            String question = "How many threats are blocked per day?";
            String context = "We have built security into every component of the network. Cloudflare’s 155 Tbps network blocks an average of 70 billion threats per day, including some of the largest DDoS attacks ever recorded. Each and every login, request, and response that goes through our network strengthens the machine learning that we apply to detect and block threats at the edge, before they ever reach your organization.";

            String answer = nlp.question(Model.robertaBaseSquad2(), false, question, context);

            System.out.println(answer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // example generation
        try {
            String text = "GPT is a powerful NLP model ";

            String answer = nlp.generation(Model.finetunedGptNeox20b(), true, text, 50);

            System.out.println(answer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}