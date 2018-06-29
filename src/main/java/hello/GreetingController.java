package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

// import hello.Greetings;
// import hello.GreetingsService;

@RestController
public class GreetingController {
    private final GreetingsService greetingsService;

    public GreetingController(GreetingsService greetingsService) {
        this.greetingsService = greetingsService;
    }

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        Greeting greetings = new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
        greetingsService.sendGreeting(greetings);
        return greetings;
    }
    // @RequestMapping("/sent")
    // @StreamListener(GreetingsStreams.INPUT)
    // public void handleGreetings(@Payload Greeting greetings) {
    //     System.out.println("Recently sent: ");

    // }
}