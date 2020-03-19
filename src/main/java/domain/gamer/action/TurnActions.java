package domain.gamer.action;

import domain.gamer.AbstractGamer;

import java.util.function.Consumer;
import java.util.function.Function;

public class TurnActions {
    private final Function<AbstractGamer, YesNo> choice;
    private final Consumer<AbstractGamer> handShower;

    public TurnActions(Function<AbstractGamer, YesNo> choice, Consumer<AbstractGamer> handShower) {
        this.choice = choice;
        this.handShower = handShower;
    }

    public boolean isHit(AbstractGamer gamer) {
        return choice.apply(gamer)
                .isYes();
    }

    public void showHand(AbstractGamer gamer) {
        handShower.accept(gamer);
    }
}
