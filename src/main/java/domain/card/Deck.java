package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final int FIRST_INDEX = 0;

    private final List<Card> cards;

    public Deck() {
        this.cards = buildDeck();
    }

    private List<Card> buildDeck() {
        var cards = new ArrayList<Card>();

        for (Suit suit : Suit.values()) {
            cards.addAll(buildCardsFrom(suit));
        }

        Collections.shuffle(cards);
        return cards;
    }

    private List<Card> buildCardsFrom(Suit suit) {
        var cards = new ArrayList<Card>();

        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(suit, denomination));
        }

        return cards;
    }

    public Card drawCard() {
        validateDrawCard();

        return cards.remove(FIRST_INDEX);
    }

    private void validateDrawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 모두 소진됐습니다.");
        }
    }

    public List<Card> cards() {
        return new ArrayList<>(cards);
    }

}
