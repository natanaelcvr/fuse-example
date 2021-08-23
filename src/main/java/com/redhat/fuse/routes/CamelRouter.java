package com.redhat.fuse.routes;

import com.redhat.fuse.pojos.Greetings;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;


/**
 * A simple Camel REST DSL route that implements the greetings service.
 * 
 */
@Component
public class CamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // @formatter:off
        restConfiguration()
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Greeting REST API")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiProperty("base.path", "camel/")
                .apiProperty("api.path", "/")
                .apiProperty("host", "")
                .apiContextRouteId("doc-api")
            .component("servlet")
            .bindingMode(RestBindingMode.json);
        
        rest("/greetings").description("Greeting to {name}")
            .get("/{name}").outType(Greetings.class)
                .route().routeId("greeting-api")
                .to("direct:greetingsImpl");
        
        rest("/send").description("Send Kafka Message")
            .get("/{message}").outType(String.class)
                .route().routeId("send-kafka-message")
                .to("direct:sendKafkaMessage");

        from("direct:greetingsImpl").description("Greetings REST service implementation route")
            .streamCaching()
            .to("bean:greetingsService?method=getGreetings");     
        // @formatter:on
    }

}