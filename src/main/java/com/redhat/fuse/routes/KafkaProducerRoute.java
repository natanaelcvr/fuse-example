package com.redhat.fuse.routes;

import com.redhat.fuse.processors.KafkaProducerProcessor;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Producer kafka route 
 *
 * @author <a href="mailto:nramalho@redhat.com">Natanael Ramalho</a>
 */
@Component
public class KafkaProducerRoute  extends RouteBuilder{

    @Autowired
    private KafkaProducerProcessor kafkaProducerProcessor;

    @Override
    public void configure() throws Exception{

        from("direct:sendKafkaMessage").id("KAFKA:PRODUCER")
            .loop(constant("{{vars.loop-count}}"))
            .process(kafkaProducerProcessor)
            .delay(300)
            .to("kafka:{{camel.component.kafka.topic.producer}}")
            .log(LoggingLevel.INFO, "\t:: KAFKA :: MESSAGE SENT :: ");
            
    }
    
}
