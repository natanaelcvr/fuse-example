package com.redhat.fuse.processors;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.kafka.KafkaConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Processo responsable to build kafka message
 *
 * @author <a href="mailto:nramalho@redhat.com">Natanael Ramalho</a>
 */
@Component
public class KafkaProducerProcessor implements Processor{

    static final Logger LOG = LoggerFactory.getLogger(KafkaProducerProcessor.class);

    @Value("${camel.component.kafka.key}")
	private String kafkaKey;

    @Override
    public void process(Exchange exchange) throws Exception {
                
        exchange.getIn().setHeader(KafkaConstants.KEY, kafkaKey);
        exchange.getIn().setBody(exchange.getIn().getHeader("message")+" - "+ new Date().getTime());
        LOG.info(":: KAFKA :: PRODUCER :: WILL SEND MESSAGE ::" + exchange.getIn().getHeader("message"));
        
    }
    
}
