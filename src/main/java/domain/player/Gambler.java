package domain.player;

import domain.player.attribute.Hand;
import domain.player.attribute.Money;
import domain.player.attribute.Name;

public class Gambler extends Participant{

    private final Money betAmount;

    public Gambler(Name name, Hand hand, Money betAmount) {
        super(name, hand);
        this.betAmount = betAmount;
    }
}
