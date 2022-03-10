package BlackJack.domain;

import java.util.List;

public class Cards {
    public static final int BUST_LINE = 21;
    public static final int EXTRA_SCORE = 10;
    private List<Card> deck;

    public Cards(List<Card> deck) {
        this.deck = deck;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void add(Card card) {
        deck.add(card);
    }

}
