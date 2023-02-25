package com.github.vbomm.nlpcloudconnector;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NlpCloud {

    private String token;
    private String baseUrl = "https://api.nlpcloud.io";
    private String apiVersion = "v1";

    public NlpCloud(String token) {
        this.token = token;
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

    private String generateBody(List<Parameter> parameters) {
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        Iterator<Parameter> iterator = parameters.iterator();
        while (iterator.hasNext()) {
            Parameter p = iterator.next();
            sb.append("\"").append(p.getName()).append("\"");
            sb.append(":");
            sb.append(p.getValue());

            if (iterator.hasNext()) sb.append(",");
        }

        sb.append("}");

        return sb.toString();
    }

    public String question(String model, boolean useGPU, String question, String context) throws IOException {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("question", question));
        parameters.add(new Parameter("context", context));

        JSONObject jsonObj = connect(generateBody(parameters), model, useGPU, "question");
        return jsonObj.getString("answer");
    }

    public String generation(String model, boolean useGPU, String text, int max_length) throws IOException {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("text", text));
        parameters.add(new Parameter("max_length", max_length));

        JSONObject jsonObj = connect(generateBody(parameters), model, useGPU, "generation");
        return jsonObj.getString("generated_text");
    }
}