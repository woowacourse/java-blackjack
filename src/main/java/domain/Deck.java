package domain;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Deck {
    private final Set<Card> cards = new HashSet<>();

    public Card poll() {
        while (true) {
            Random random = new Random();
            int number = random.nextInt(1, 59);
            int symbolNumber = random.nextInt(4);

            Card card = new Card(number, Symbol.getSymbol(symbolNumber));
            boolean hasNoCard = cards.add(card);

            if (hasNoCard) {
                return card;
            }
        }
    }
}
