package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.restdocs.SpringCloudContractRestDocs;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.function.Consumer;

@SpringBootTest
@AutoConfigureRestDocs
@AutoConfigureWebTestClient
class DemoApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void contextLoads() {
    }

    @Test
    void home() throws Exception {
        Consumer<EntityExchangeResult<byte[]>> document = WebTestClientRestDocumentation
                .document(
                        "{method-name}",
                        HeaderDocumentation.requestHeaders(
                        ),
                        RequestDocumentation.pathParameters(
                        ),
                        HeaderDocumentation.responseHeaders(
                                HeaderDocumentation.headerWithName("Content-Type").description(
                                        "Content type")
//                        ),
//                        PayloadDocumentation.responseFields(
//                                PayloadDocumentation.fieldWithPath("gitProperties").description("git properties"),
//                                PayloadDocumentation.fieldWithPath("gitProperties.commitId").description("git properties"),
//                                PayloadDocumentation.fieldWithPath("buildProperties").description("build properties")
                        ),
                        SpringCloudContractRestDocs.dslContract()
                );
        this.webTestClient.get().uri("/").exchange()
                .expectStatus().isOk()
                .expectBody().consumeWith(document);
    }
}
