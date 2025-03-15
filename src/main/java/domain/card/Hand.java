package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Hand {
    private static final int ORIGINAL_ACE_VALUE = 11;
    private static final int BLACKJACK_MAX_SCORE = 21;
    private static final int INITIAL_SIZE = 2;
    private static final int AFTER_APPLY_BUST_PENALTY_SCORE = 0;

    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(final Card... cardsInput) {
        cards.addAll(Arrays.asList(cardsInput));
    }

    public boolean containsOriginalAce() {
        return cards.stream().anyMatch(originalAcePredicate());
    }

    public void setOriginalAceValueToOne() {
        Card originalAce = cards.stream()
                .filter(originalAcePredicate())
                .findFirst()
                .orElseThrow();
        originalAce.setValueToOne();
    }

    public boolean isBust() {
        return getTotal() > BLACKJACK_MAX_SCORE || getTotal() == AFTER_APPLY_BUST_PENALTY_SCORE;
    }

    public boolean isBlackJack() {
        return getSize() == INITIAL_SIZE && isMaxScore();
    }

    public boolean isMaxScore() {
        return getTotal() == BLACKJACK_MAX_SCORE;
    }

    public void setAllCardValueToZero() {
        for (Card card : cards) {
            card.setValueToZero();
        }
    }

    private Predicate<Card> originalAcePredicate() {
        return card -> card.isAce() && card.getValue() == ORIGINAL_ACE_VALUE;
    }


    public int getTotal() {
        return cards.stream().mapToInt(Card::getValue).sum();
    }

    public int getSize() {
        return cards.size();
    }

    public int getExtraSize() {
        return getSize() - INITIAL_SIZE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }
}
