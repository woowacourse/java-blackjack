package blackjack.domain.random;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardRandomGenerator implements CardGenerator {

    public static final List<Card> CARDS = initializeCards();

    @Override
    public Card pickRandomCard() {
        final List<Card> cards = new ArrayList<>(CARDS);
        Collections.shuffle(cards);
        return cards.getFirst();
    }

    private static List<Card> initializeCards() {
        final List<Card> cards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            addCard(shape, cards);
        }
        return cards;
    }

    private static void addCard(final Shape shape, final List<Card> cards) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(shape, denomination));
        }
    }
}
