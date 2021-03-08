package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Challenger extends Player {

    public Challenger(final Cards cards, final Name name) {
        super(cards, name);
    }

    @Override
    public List<Card> getInitCardsAsList() {
        return hand.getCardsAsList();
    }
}
