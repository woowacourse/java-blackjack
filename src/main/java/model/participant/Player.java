package model.participant;

import java.util.List;
import model.card.Card;
import model.game.BettingAmount;
import model.participant.exception.ForbiddenPlayerNameException;

public class Player extends Participant {
    private final BettingAmount bettingAmount;

    private Player(String name, BettingAmount bettingAmount) {
        super(name);
        this.bettingAmount = bettingAmount;
    }

    public static Player of(String name, int bettingAmount) {
        validateName(name);
        BettingAmount amount = BettingAmount.from(bettingAmount);

        return new Player(name, amount);
    }

    private static void validateName(String name) {
        if (name.equals(Dealer.NAME)) {
            throw new ForbiddenPlayerNameException(name);
        }
    }

    @Override
    public List<Card> open() {
        return hand.asList();
    }

    public boolean beats(Dealer dealer) {
        if (isBust()) {
            return false;
        }

        if (isBlackjack()) {
            return true;
        }

        if (dealer.isBust()) {
            return true;
        }

        if (dealer.isBlackjack()) {
            return false;
        }

        return calculateScore() >= dealer.calculateScore();
    }

    public BettingAmount getBettingAmount() {
        return this.bettingAmount;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + getName() + '\'' +
                "hand=" + hand +
                '}';
    }
}
