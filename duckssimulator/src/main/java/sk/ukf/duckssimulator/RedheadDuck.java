package sk.ukf.duckssimulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sk.ukf.duckssimulator.behavior.FlyBehavior;
import sk.ukf.duckssimulator.behavior.QuackBehavior;

@Component
public class RedheadDuck implements Duck{

    private QuackBehavior quackBehavior;
    private FlyBehavior flyBehavior;

    @Autowired
    public RedheadDuck(@Qualifier("quackSound") QuackBehavior quackBehavior, @Qualifier("flyWithWings") FlyBehavior flyBehavior) {
        this.quackBehavior = quackBehavior;
        this.flyBehavior = flyBehavior;
    }

    @Override
    public String display() {
        return "Tu je";
    }

    @Override
    public String swim() {
        return "bul-bul-bul";
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
