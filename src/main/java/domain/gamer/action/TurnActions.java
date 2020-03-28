package domain.gamer.action;

import domain.gamer.Gamer;

import java.util.function.Consumer;
import java.util.function.Function;

public class TurnActions {
    private final Function<Gamer, YesNo> choice;
    private final Consumer<Gamer> handShower;

    public TurnActions(Function<Gamer, YesNo> choice, Consumer<Gamer> handShower) {
        this.choice = choice;
        this.handShower = handShower;
    }

    public boolean isHit(Gamer gamer) {
        return choice.apply(gamer)
                .isYes();
    }

    public void showHand(Gamer gamer) {
        handShower.accept(gamer);
    }
}
