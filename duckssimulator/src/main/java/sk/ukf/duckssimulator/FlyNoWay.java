package sk.ukf.duckssimulator;

import org.springframework.stereotype.Component;
import sk.ukf.duckssimulator.behavior.FlyBehavior;

@Component
public class FlyNoWay implements FlyBehavior {

    @Override
    public String fly() {
        return "I cannot fly";
    }
}
