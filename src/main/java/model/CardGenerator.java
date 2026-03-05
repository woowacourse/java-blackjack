package model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CardGenerator {

    private final Set<Card> usedCards = new HashSet<>();

    public Card generateCard() {
        while(true) {
            Random random = new Random();
            int index = random.nextInt(4);
            int number = random.nextInt(13) + 1;

            CardShape shape = CardShape.from(index);
            CardValue value = CardValue.from(number);
            Card card = new Card(shape, value);

            if(!usedCards.contains(card)) {
                usedCards.add(card);
                return card;
            }
        }
    }
}
