package domain.deck;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suits;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class RandomDeckGenerator implements DeckGenerator {
    private static void addCard(final List<Card> cards, final Suits suits) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(denomination, suits));
        }
    }

    @Override
    public Deck generateDeck() {
        List<Card> cards = new ArrayList<>();
        for (Suits suits : Suits.values()) {
            addCard(cards, suits);
        }
        Collections.shuffle(cards);
        return new Deck(new ArrayDeque<>(cards));
    }
}
