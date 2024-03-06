package domain;

import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateSum() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int countAceCard() {
        return (int) this.cards.stream()
                .filter(Card::isAceCard)
                .count();
    }

    public int calculateScore() {
        int aceCount = countAceCard();
        int sum = calculateSum();

        while (sum > 21 && aceCount > 0) {
            aceCount--;
            sum -= 10;
        }

        return sum;
    }

    public List<Card> getCards() {
        return cards;
    }
}
