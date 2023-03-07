package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private final Random random = new Random();
    private final List<Card> cards;

    public Deck() {
        this.cards = buildDeck();
    }

    private List<Card> buildDeck() {
        var cards = new ArrayList<Card>();
        var suits = List.of(Suit.values());

        for (Suit suit : suits) {
            cards.addAll(buildCardsFrom(suit));
        }

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

        return cards.remove(pickRandomCard());
    }

    private void validateDrawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 모두 소진됐습니다.");
        }
    }

    private int pickRandomCard() {
        return random.nextInt(cards.size());
    }

    public List<Card> getCards() {
        return cards;
    }

}
