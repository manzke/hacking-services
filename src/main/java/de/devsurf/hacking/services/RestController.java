package de.devsurf.hacking.services;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

// The Java class will be hosted at the URI path "/helloworld"
@org.springframework.web.bind.annotation.RestController()
@RequestMapping("/api/v1")
public class RestController {
    // The Java method will process HTTP GET requests
    //@GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @GetMapping(value = "/messages", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<String> getClichedMessages() {
        // Return some cliched textual content
//        return Flux.just("Hello World");
        return Flux.interval(Duration.ofSeconds(1)).map(message -> Long.toString(new Random().nextLong()));
    }

    @GetMapping("/messages/{messageId}")
    public Mono<String> getClichedMessage(@PathVariable String messageId) {
        // Return some cliched textual content
        return getClichedMessages().next();
    }
}
