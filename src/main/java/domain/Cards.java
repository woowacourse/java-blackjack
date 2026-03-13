package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int getSize() {
        return cards.size();
    }

    public List<String> getCardsInfo() {
        return cards.stream()
                .map(Card::getCardInfo)
                .toList();
    }

    public Card removeFirst() {
        return cards.removeFirst();
    }

    public int sumScore() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getScore();
        }
        return sum;
    }

    public void add(Card card) {
        cards.add(card);
    }
}
