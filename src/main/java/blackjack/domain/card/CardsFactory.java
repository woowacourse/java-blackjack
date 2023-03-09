package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardsFactory {

    private static final List<Card> cards = new ArrayList<>();

    static {
        generateCards();
    }

    private CardsFactory() {
    }

    private static void generateCards() {
        for (final Rank rank : Rank.values()) {
            addCard(rank);
        }
    }

    private static void addCard(final Rank rank) {
        for (final Suit suit : Suit.values()) {
            cards.add(new Card(rank, suit));
        }
    }

    public static List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
