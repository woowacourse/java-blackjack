package domain;

import java.util.List;

import static domain.Player.BLACK_JACK;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateSum() {
        return cards.stream()
                .mapToInt(Card::getLetterValue)
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

        while (isBustWithAce(sum, aceCount)) {
            aceCount--;
            sum -= 10;
        }

        return sum;
    }

    private boolean isBustWithAce(int sum, int aceCount) {
        return sum > BLACK_JACK && aceCount > 0;
    }

    public List<Card> getCards() {
        return cards;
    }
}
