package blackjack.domain.card;


import blackjack.domain.card.Card;
import java.util.List;

public class Cards {
    List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
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
