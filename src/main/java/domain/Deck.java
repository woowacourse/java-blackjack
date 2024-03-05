package domain;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Deck {
    private static final Random random = new Random();
    private final Set<Card> cards = new HashSet<>();

    public Card poll() {
        while (true) {
            final Denomination denomination = Denomination.getDenomination(random.nextInt(0, 13));
            final Symbol symbol = Symbol.getSymbol(random.nextInt(4));

            final Card card = new Card(denomination, symbol);
            final boolean hasNoCard = cards.add(card);

            if (hasNoCard) {
                return card;
            }
        }
    }
}
