package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int BUST_THRESHOLD = 21;

    private final List<Card> cards = new ArrayList<>();

    public void addCards(final List<Card> cards) {
        this.cards.addAll(cards);
    }

    public int calculateCardScore() {
        int sum = cards.stream()
                .mapToInt(Card::getValue)
                .sum();

        int count = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        while (count > 0 && sum + 10 <= BUST_THRESHOLD) {
            sum += 10;
            count--;
        }

        return sum;
    }

    public List<Card> getCards() {
        return cards;
    }
}
