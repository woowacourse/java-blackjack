package blackjack.domain.card;

import static blackjack.domain.game.Round.BLACKJACK;
import static blackjack.domain.card.Card.MAX_ACE_VALUE;
import static blackjack.domain.card.Card.MIN_ACE_VALUE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hands {
    private final List<Card> cards;

    public Hands() {
        this.cards = new ArrayList<>();
    }

    public boolean hasSize(final int size) {
        return cards.size() == size;
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public boolean isScoreExceed(final int criteria) {
        return calculateScore() > criteria;
    }

    public int calculateScore() {
        return adjustSumByAce(calculateCardsSum(), countAce());
    }

    private int calculateCardsSum() {
        return cards.stream()
                .mapToInt(Card::getTypeValue)
                .sum();
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int adjustSumByAce(int sum, int aceCount) {
        if (sum <= BLACKJACK) {
            return sum;
        }
        while (aceCount > 0 && sum > BLACKJACK) {
            sum -= (MAX_ACE_VALUE - MIN_ACE_VALUE);
            aceCount--;
        }
        return sum;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
