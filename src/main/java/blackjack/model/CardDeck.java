package blackjack.model;

import java.util.List;
import java.util.Random;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck(List<Card> cards) {
        this.cards = cards;
    }

    public Card draw() {
        // 카드를 덱에서 1장 드로우 한다.
        Random random = new Random();
        int randomIndex = random.nextInt(0, cards.size());

        return cards.get(randomIndex);
    }
}
