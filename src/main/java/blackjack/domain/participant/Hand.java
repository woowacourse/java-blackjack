package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;

public class Hand {
    public static final int FIRST_CARD_INDEX = 0;
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        if (canAddTen(score)) {
            score += 10;
        }
        return score;
    }

    private boolean canAddTen(int score) {
        return containAce() && score <= 11;
    }

    private boolean containAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public Card pickFirstCard() {
        return cards.get(FIRST_CARD_INDEX);
    }
}
