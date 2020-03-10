package blackjack.cards;

import java.util.HashSet;
import java.util.Set;

public class CardDeck {
    private static final Set<Card> cards;
    static {
        cards = new HashSet<>();
        for (CardNumber cardNumber : CardNumber.values()) {
            for (Figure figure : Figure.values()) {
                cards.add(new Card(cardNumber, figure));
            }
        }
    }

    public static int getSize() {
        return cards.size();
    }
}
