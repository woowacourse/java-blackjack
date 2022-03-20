package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import blackjack.domain.game.GameResult;

public class Player extends Participant {
    private final BettingAmount bettingAmount;

    public Player(Name name, BettingAmount bettingAmount) {
        super(name);
        this.bettingAmount = bettingAmount;
    }

    public Player(Name name, Cards cards, BettingAmount bettingAmount) {
        this(name, bettingAmount);
        super.cards = cards;
    }

    public long getBettingAmount() {
        return bettingAmount.getValue();
    }

    @Override
    public boolean isFinished() {
        return cards.sum() >= GameResult.BLACKJACK_VALUE;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }
}
