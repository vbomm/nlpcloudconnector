package com.github.vbomm.nlpcloudconnector;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class NlpCloud {

    private String token;
    private String baseUrl = "https://api.nlpcloud.io";
    private String apiVersion = "v1";

    public NlpCloud(String token) {
        this.token = token;
    }

    public String question(String model, boolean useGPU, String question, String context) throws IOException {
        Request request = Request.Post(
                    baseUrl + "/" +
                        apiVersion + "/" +
                        (useGPU ? "gpu/" : "") +
                        model + "/" +
                        "question");

        String body = "{" +
                "\"question\":\"" + question + "\"," +
                "\"context\":\"" + context + "\" " +
                "}";

        request.bodyString(body, ContentType.APPLICATION_JSON);
        request.setHeader("Authorization", "Token " + token);
        request.setHeader("Content-Type", "application/json");

        HttpResponse httpResponse = request.execute().returnResponse();

        if (httpResponse.getEntity() != null && httpResponse.getStatusLine().getStatusCode() == 200) {
            String response = EntityUtils.toString(httpResponse.getEntity());

            JSONObject jsonObj = new JSONObject(response);

            return jsonObj.getString("answer");
        } else throw new HttpResponseException(httpResponse.getStatusLine().getStatusCode(), httpResponse.getStatusLine().getReasonPhrase());
    }
}