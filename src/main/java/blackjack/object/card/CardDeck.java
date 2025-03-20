package blackjack.object.card;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private final List<Card> cards;
    private int index;

    public CardDeck(final List<Card> cards, final Shuffler shuffler) {
        List<Card> shuffleCards = shuffler.shuffle(cards);
        this.cards = new ArrayList<>(shuffleCards);
        this.index = 0;
    }

    public Card getCard() {
        return cards.get(index++);
    }
}
