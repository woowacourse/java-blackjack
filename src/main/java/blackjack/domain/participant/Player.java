package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Player extends Participant {

    public Player(final Cards cards, final String name) {
        super(cards, name);
    }

    @Override
    public List<Card> getInitCardsAsList() {
        return hand.getCardsAsList();
    }
}
