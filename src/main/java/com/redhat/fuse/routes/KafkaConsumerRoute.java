package com.redhat.fuse.routes;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Route to consume kafka topic
 *
 * @author <a href="mailto:nramalho@redhat.com">Natanael Ramalho</a>
 */
@Component
public class KafkaConsumerRoute  extends RouteBuilder{


    @Override
    public void configure() throws Exception{

        from("kafka:{{camel.component.kafka.topic.consumer}}?brokers=localhost:9093")
            .log("Message received from Kafka : ${body}")
            .log("    on the topic ${headers[kafka.TOPIC]}")
            .log("    on the partition ${headers[kafka.PARTITION]}")
            .log("    with the offset ${headers[kafka.OFFSET]}")
            .log("    with the key ${headers[kafka.KEY]}");
            
    }
    
}
