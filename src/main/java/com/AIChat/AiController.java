package com.AIChat;

import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AiController {

    private OllamaChatClient client;

    public static final String PROMPT="Explain why to learn java in 2024";

    public AiController(OllamaChatClient client) {
        this.client = client;
    }

    @GetMapping("/prompt")
    public String promptResponse(){
    String response=client.call(PROMPT);
    return response;
    }

    @GetMapping("/query")
    public String dymanicRespose(@RequestParam("query") String query){
    String dynamicResponse=client.call(query);
    return dynamicResponse;
    }

    @GetMapping("/chunks")
    public Flux<String> getResponseInChunks(@RequestParam("chunks") String chunks){
        Flux<String> chunksResponse=client.stream(chunks);
        return chunksResponse;
    }
}
