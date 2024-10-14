package sk.ukf.duckssimulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private Duck mallardDuck, redheadDuck, rubberDuck;

    @Autowired
    @Qualifier("mallardDuck")
    public void setMallardDuck(Duck mallardDuck) {
        this.mallardDuck = mallardDuck;
    }

    @Autowired
    @Qualifier("redheadDuck")
    public void setRedheadDuck(Duck redheadDuck) {
        this.redheadDuck = redheadDuck;
    }

    @Autowired
    @Qualifier("rubberDuck")
    public void setRubberDuck(Duck rubberDuck) {
        this.rubberDuck = rubberDuck;
    }

    @GetMapping("/")
    public String toString() {
        String response = "";

        response += mallardDuck.display() + "<br>\n";
        response += mallardDuck.swim() + "<br>\n";
        response += mallardDuck.performQuack() + "<br>\n";
        response += mallardDuck.fly() + "<br>\n";
        response += "<br>\n";
        response += rubberDuck.display() + "<br>\n";
        response += rubberDuck.swim() + "<br>\n";
        response += rubberDuck.performQuack() + "<br>\n";
        response += rubberDuck.fly() + "<br>\n";
        response += "<br>\n";
        response += redheadDuck.display() + "<br>\n";
        response += redheadDuck.swim() + "<br>\n";
        response += redheadDuck.performQuack() + "<br>\n";
        response += redheadDuck.fly() + "<br>\n";

        return response;
    }
}