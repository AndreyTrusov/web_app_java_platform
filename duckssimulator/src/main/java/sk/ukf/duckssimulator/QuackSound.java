package sk.ukf.duckssimulator;

import org.springframework.stereotype.Component;
import sk.ukf.duckssimulator.behavior.QuackBehavior;

@Component
public class QuackSound implements QuackBehavior {
    @Override
    public String quack() {
        return "Kvákam (nie je to otravné).";
    }
}
