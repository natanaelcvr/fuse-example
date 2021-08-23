package com.redhat.fuse.services.impl;


import com.redhat.fuse.pojos.Greetings;
import com.redhat.fuse.services.GreetingsService;

import org.apache.camel.Header;
import org.springframework.stereotype.Service;

@Service("greetingsService")
public class GreetingsServiceImpl implements GreetingsService {

    private static final String THE_GREETINGS = "Hello, ";

    @Override
    public Greetings getGreetings(@Header("name") String name ) {
        return new Greetings( THE_GREETINGS + name );
    }

}