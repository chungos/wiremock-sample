package com.botbots.wiremock.test;

import static org.junit.Assert.assertEquals;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class AppTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void runHelloWorldFiletest() {
        stubFor(get(urlEqualTo("/api/helloworld"))
                .willReturn(aResponse()
                        .withBodyFile("hello.json")));

        URL uriFile = null;
        try {
            uriFile = new URL("http://localhost:8089/api/helloworld");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream contentFile = null;
        try {
            contentFile = uriFile.openConnection().getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String retrievedBodyFile = "";
        try {
            retrievedBodyFile = IOUtils.toString(contentFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("{\n" +
                "  \"request\": {\n" +
                "    \"method\": \"GET\",\n" +
                "    \"url\": \"/api/helloworld\"\n" +
                "  },\n" +
                "  \"response\": {\n" +
                "    \"status\": 200,\n" +
                "    \"body\": \"Hello world via a file!\",\n" +
                "    \"headers\": {\n" +
                "      \"Content-Type\": \"text/plain\"\n" +
                "    }\n" +
                "  }\n" +
                "}", retrievedBodyFile);

    }

    @Test
    public void runHelloWorldLiteraltest() {

        stubFor(get(urlEqualTo("/body"))
                .willReturn(aResponse()
                        .withBody("Literal text to put in the body")));

        URL uriLiteral = null;
        try {
            uriLiteral = new URL("http://localhost:8089/body");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream contentLiteral = null;
        try {
            contentLiteral = uriLiteral.openConnection().getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String retrievedBodyLiteral = "";
        try {
            retrievedBodyLiteral = IOUtils.toString(contentLiteral);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("Literal text to put in the body", retrievedBodyLiteral);


    }
}