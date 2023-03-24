package domain.participant;

import domain.card.Cards;

public class Player extends Participant {

    public Player(Name name, Cards cards, Money money) {
        super(name, cards, money);
    }

    @Override
    public boolean canReceiveOneMoreCard() {
        return cards.calculateSum() < cards.BLACKJACK_NUMBER;
    }
}
