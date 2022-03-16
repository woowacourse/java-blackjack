package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Status;

public class Player extends Participant {
    private final BettingAmount bettingAmount;

    public Player(Name name, Cards cards, BettingAmount bettingAmount) {
        super(name, cards);
        this.bettingAmount = bettingAmount;
    }

    public long getBettingAmount() {
        return bettingAmount.getValue();
    }

    @Override
    public boolean isFinished() {
        return cards.getStatus() != Status.NONE;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }
}
