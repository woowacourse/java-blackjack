package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    public static final int NUMBER_OF_FIRST_DEAL_CARDS = 2;

    private List<Card> deck;

    public Deck(List<Card> deck) {
        this.deck = deck;
    }

    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    public Card deal() {
        return this.deck.remove(0);
    }

    public List<Card> dealFirstCards() {
        List<Card> twoCards = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_FIRST_DEAL_CARDS; i++) {
            twoCards.add(this.deal());
        }
        return Collections.unmodifiableList(twoCards);
    }

}
