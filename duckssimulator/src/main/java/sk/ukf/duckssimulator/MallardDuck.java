package sk.ukf.duckssimulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sk.ukf.duckssimulator.behavior.FlyBehavior;
import sk.ukf.duckssimulator.behavior.QuackBehavior;

@Component
public class MallardDuck implements Duck {
    private QuackBehavior quackBehavior;
    @Autowired
    public MallardDuck(@Qualifier("quackSound") QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
    @Override
    public String display() {
        return "Zobrazuje sa divá kačica.";
    }
    @Override
    public String swim() {
        return "Divá kačica pláva.";
    }

    @Override
    public String performQuack() {
        return quackBehavior.quack();
    }
}