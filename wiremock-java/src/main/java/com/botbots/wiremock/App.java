package com.botbots.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Hello world!
 *
 */
public class App 
{

    static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
        WireMockServer wireMockServer = new WireMockServer(options()
                                                .port(8089)
                                                .usingFilesUnderDirectory("src/main/resources/"));

        LOG.info("Starting wiremock server");
        wireMockServer.start();


        setURLmappings();

    }


    private static void setURLmappings() {
        configureFor(8089);
        stubFor(get(urlEqualTo("/api/helloworld"))
                .willReturn(aResponse()
                            .withBodyFile("hello.json")));

        stubFor(get(urlEqualTo("/body"))
                .willReturn(aResponse()
                        .withBody("Literal text to put in the body")));
    }


}

