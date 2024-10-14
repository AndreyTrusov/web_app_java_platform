package sk.ukf.duckssimulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private Duck myDuck;

    @Autowired
    @Qualifier("redheadDuck")
    public void setMyDuck(Duck myDuck) {
        this.myDuck = myDuck;
    }

    @GetMapping("/")
    public String toString() {
        return myDuck.display() + " " + myDuck.swim();
    }
}