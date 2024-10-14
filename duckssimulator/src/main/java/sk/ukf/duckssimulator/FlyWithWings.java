package sk.ukf.duckssimulator;

import org.springframework.stereotype.Component;
import sk.ukf.duckssimulator.behavior.FlyBehavior;

@Component
public class FlyWithWings implements FlyBehavior {

    @Override
    public String fly() {
        return "I can fly!";
    }
}
