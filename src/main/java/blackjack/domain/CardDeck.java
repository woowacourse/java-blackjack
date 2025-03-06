package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private final Shuffler shuffler;
    private final List<Card> cards;
    private int index;

    public CardDeck(final List<Card> cards, final Shuffler shuffler) {
        List<Card> shuffleCards = shuffler.shuffle(cards);
        this.cards = new ArrayList<>(shuffleCards);
        this.shuffler = shuffler;
        this.index = 0;
    }

    private List<Card> shuffle(final List<Card> cards) {
        return shuffler.shuffle(cards);
    }

    public Card getCard() {
        return cards.get(index++);
    }
}
