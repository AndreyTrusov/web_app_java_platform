package sk.ukf.duckssimulator.behavior;

import org.springframework.stereotype.Component;

@Component
public class QuackSound implements QuackBehavior {
    @Override
    public String quack() {
        return "Kvákam (je to otravné).";
    }
}
