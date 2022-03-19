package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int BLACKJACK_SCORE = 21;
    private static final int ANOTHER_ACE_SCORE = 10;
    private static final int BLACKJACK_SIZE = 2;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(final Card card) {
        validateCard(card);
        cards.add(card);
    }

    private void validateCard(final Card card) {
        if (card == null) {
            throw new IllegalArgumentException("[ERROR] 올바른 카드를 입력해주세요.");
        }
    }

    public int calculateEndScore() {
        final int score = calculateScore();
        if (score <= BLACKJACK_SCORE) {
            return score;
        }
        return calculateDefaultScore();
    }

    public int calculateScore() {
        if (containsAce()) {
            return calculateDefaultScore() + ANOTHER_ACE_SCORE;
        }
        return calculateDefaultScore();
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int calculateDefaultScore() {
        return cards.stream()
                .mapToInt(card -> card.getScore().getAmount())
                .sum();
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_SIZE && calculateEndScore() == BLACKJACK_SCORE;
    }

    public boolean isBust() {
        return calculateEndScore() > BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
