package sk.ukf.duckssimulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sk.ukf.duckssimulator.behavior.FlyBehavior;
import sk.ukf.duckssimulator.behavior.QuackBehavior;

@Component
public class RubberDuck implements Duck {

    private QuackBehavior quackBehavior;
    private final FlyBehavior flyBehavior;

    @Autowired
    public RubberDuck(@Qualifier("quackSound") QuackBehavior quackBehavior, @Qualifier("flyNoWay") FlyBehavior flyBehavior) {
        this.quackBehavior = quackBehavior;
        this.flyBehavior = flyBehavior;
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

    @Override
    public String fly() {
        return flyBehavior.fly();
    }
}