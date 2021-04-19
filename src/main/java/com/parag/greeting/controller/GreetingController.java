package com.parag.greeting.controller;


import com.parag.greeting.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


import java.util.concurrent.atomic.AtomicLong;
@RestController
@RequestMapping ("/greeting")
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter =new AtomicLong();

//    curl -X GET "http://localhost:8080/greeting/query/?fName=Parag&lName=Lande"
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String sayHello(@RequestParam(value = "fName") String fName, @RequestParam(value = "lName") String lName){
            return "Hello " + fName + " "+ lName;
    }

    @GetMapping(value = {"","/","/home"})
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        return new Greeting(counter.incrementAndGet(), String.format(template,name));
    }


//    curl -X POST -H "Content-Type: application/json" -d '{"id":123,"content":"Hello"}' "http://localhost:8080/greeting/post" -w "\n"
    @PostMapping("/post")
    public String greetingPost(@RequestBody Greeting greeting){
        return greeting.getId()+ " "+ greeting.getContent()+" !!";
    }

//    curl -X PUT "http://localhost:8080/greeting/put/34?content=PArag"
    @PutMapping("/put/{id}")
    public Greeting greeting(@PathVariable("id") int id, @RequestParam(value = "content") String content){
        return  new Greeting(id,String.format(template, content));
    }

//    curl -X PUT "http://localhost:8080/greeting/put/greeting/?name=PArag"
    @PutMapping("/put/greeting")
    public Greeting putGreeting(@RequestParam(value = "name") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
