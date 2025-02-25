package sk.ukf.duckssimulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sk.ukf.duckssimulator.behavior.FlyBehavior;
import sk.ukf.duckssimulator.behavior.QuackBehavior;

@Component // @Component("mallardDuck")
public class MallardDuck implements Duck {

    private QuackBehavior quackBehavior;
    private FlyBehavior flyBehavior;

    @Autowired // @Autowired: Označuje, že Spring má automaticky injektovať závislosti cez tento konštruktor.
    public MallardDuck(@Qualifier("quackSound") QuackBehavior quackBehavior, @Qualifier("flyWithWings") FlyBehavior flyBehavior) {
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