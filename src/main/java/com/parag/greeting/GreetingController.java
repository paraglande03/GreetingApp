package com.parag.greeting;


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


    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String sayHello(@RequestParam(value = "fname") String fName, @RequestParam(value = "lName") String lName){
            return "Hello" + fName + " "+ lName;
    }

    @GetMapping(value = {"","/","/home"})
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        return new Greeting(counter.incrementAndGet(), String.format(template,name));
    }

    @PostMapping("/post")
    public String grettingPost(@RequestBody Greeting greeting){
        return greeting.getId()+ " "+ greeting.getContent()+" !!";
    }

    @PutMapping("/put/{firstName}")
    public String sayHelloPut(@PathVariable String firstname , @RequestParam(value = "lastName") String lastName){
        return "Hello " + firstname + " "+ lastName + " !";
    }

}
