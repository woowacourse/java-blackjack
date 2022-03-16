package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardsForBlackJack {

    public static final List<Card> cards = CardsForBlackJack.gatherCards();

    private static List<Card> gatherCards() {
        List<Card> cards = new ArrayList<>();
        createCards(cards);
        Collections.shuffle(cards);
        return Collections.unmodifiableList(cards);
    }

    private static void createCards(List<Card> cards) {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }
}
