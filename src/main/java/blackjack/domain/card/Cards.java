package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards = new ArrayList<>();

    public Cards(Card firstCard, Card secondCard) {
        cards.add(firstCard);
        cards.add(secondCard);
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && sumCardsScore() == 21;
    }

    private int sumCardsScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }
}