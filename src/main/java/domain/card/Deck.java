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
        if (deck.size() < 1) {
            throw new IllegalStateException("덱에서 나누어주지 않은 카드가 한장도 없습니다.");
        }
        return this.deck.remove(0);
    }

    public List<Card> dealFirstCards() {
        List<Card> firstDealtCards = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_FIRST_DEAL_CARDS; i++) {
            firstDealtCards.add(this.deal());
        }
        return Collections.unmodifiableList(firstDealtCards);
    }
}
