package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {

    private static final List<Card> CARDS = new ArrayList<>();

    static {
        for (CardSuit suit : CardSuit.values()) {
            generateCard(suit);
        }
    }

    private static void generateCard(CardSuit suit) {
        for (CardNumber number : CardNumber.values()) {
            CARDS.add(new Card(suit, number));
        }
    }

    public static List<Card> values() {
        return List.copyOf(CARDS);
    }
}
