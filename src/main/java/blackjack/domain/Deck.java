package blackjack.domain;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Card;
import blackjack.strategy.ShuffleStrategy;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck(final ShuffleStrategy shuffleStrategy) {
        this.cards = CardFactory.createCards();
        shuffleStrategy.shuffle(cards);
    }

    public List<Card> getCards() {
        return cards;
    }
}
