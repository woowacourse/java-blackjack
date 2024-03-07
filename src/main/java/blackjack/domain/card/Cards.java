package blackjack.domain.card;


import java.util.ArrayList;
import java.util.List;

public class Cards {
    List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int sum() {
        return cards.stream()
                    .mapToInt(Card::getCardScore)
                    .sum();
    }

    public boolean containAce() {
        return cards.stream()
                    .anyMatch(Card::isAce);
    }

    public List<Card> toList() {
        return cards;
    }
}
