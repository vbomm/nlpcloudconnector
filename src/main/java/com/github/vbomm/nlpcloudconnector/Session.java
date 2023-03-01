package com.github.vbomm.nlpcloudconnector;

import com.github.vbomm.nlpcloudconnector.Generation.Generation;
import com.github.vbomm.nlpcloudconnector.Question.Question;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class Session {

    private String token;
    private String baseUrl = "https://api.nlpcloud.io";
    private String apiVersion = "v1";

    public Session(String token) {
        this.token = token;
    }

    public String request(Question question) throws IOException {
        String body = "" +
                "{" +
                "\"question\":" + "\"" + question.getQuestion() + "\"," +
                "\"context\":" + "\"" + question.getContext() + "\"" +
                "}";

        JSONObject jsonObj = connect(body, question.getModel(), question.isGpu(), "question");

        return jsonObj.getString("answer");
    }

    public String request(Generation generation) throws IOException {
        String body = "" +
                "{" +
                "\"text\":" + "\"" +generation.getText() + "\"," +
                "\"max_length\":" + generation.getMax_length() +
                "}";

        JSONObject jsonObj = connect(body, generation.getModel(), generation.isGpu(), "generation");

        return jsonObj.getString("generated_text");
    }

    private JSONObject connect(String body, String model, boolean useGPU, String endpoint) throws IOException {
        Request request = Request.Post(
                baseUrl + "/" +
                        apiVersion + "/" +
                        (useGPU ? "gpu/" : "") +
                        model + "/" +
                        endpoint);

        request.bodyString(body, ContentType.APPLICATION_JSON);
        request.setHeader("Authorization", "Token " + token);
        request.setHeader("Content-Type", "application/json");

        HttpResponse httpResponse = request.execute().returnResponse();

        if (httpResponse.getEntity() != null && httpResponse.getStatusLine().getStatusCode() == 200) {
            String response = EntityUtils.toString(httpResponse.getEntity());

            return new JSONObject(response);
        } else throw new HttpResponseException(httpResponse.getStatusLine().getStatusCode(), httpResponse.getStatusLine().getReasonPhrase());
    }
}
