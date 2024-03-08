package blackjack.domain.card;


import java.util.ArrayList;
import java.util.List;

public class Cards {
    private List<Card> cards;

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

    public void add(Card card) {
        cards.add(card);
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public List<Card> toList() {
        return cards;
    }
}
