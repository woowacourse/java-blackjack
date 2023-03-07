package blackjack.domain;

import static blackjack.domain.Denomination.values;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackDeckGenerator implements DeckGenerator {

    private static List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            addCards(cards, suit);
        }
        return cards;
    }

    private static void addCards(List<Card> cards, Suit suit) {
        for (Denomination denomination : values()) {
            cards.add(new Card(suit, denomination));
        }
    }

    @Override
    public Deck generate() {
        List<Card> cards = createCards();
        Collections.shuffle(cards);
        return new Deck(cards);
    }
}
