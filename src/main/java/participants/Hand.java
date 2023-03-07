package participants;

import java.util.ArrayList;
import java.util.List;

import card.Card;

public class Hand {
    public static final int ACE_ADDITIONAL_SCORE = 10;
    public static final int LIMIT_ADDITIONAL_SCORE = 11;
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        if (canAddTen(score)) {
            score += ACE_ADDITIONAL_SCORE;
        }
        return score;
    }

    private boolean canAddTen(int score) {
        return containAce() && score <= LIMIT_ADDITIONAL_SCORE;
    }

    private boolean containAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public Card pickFirstCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("들고있는 카드가 없습니다.");
        }
        return cards.get(0);
    }
}
