package blackjack.domain.participant;

import blackjack.domain.card.Cards;

public class Player extends Participant {

    public Player(Name name, Cards cards) {
        super(name, cards);
    }

    @Override
    public boolean isFinished() {
        return cards.isBust() || cards.isBlackJack();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }
}
