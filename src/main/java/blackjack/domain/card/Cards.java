package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public String getCardsInfoToString() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }

    public String getFirstCardInfoToString() {
        return cards.get(0).toString();
    }
}