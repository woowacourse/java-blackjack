package domain.card;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CardHand {
    private static final int MIN_ACE_SCORE = 1;
    private static final int MAX_ACE_SCORE = 11;
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int MAX_SCORE = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final Set<Card> cards;

    public CardHand(Set<Card> cards) {
        this.cards = new LinkedHashSet<>(cards);
    }

    public void add(Card newCard) {
        cards.add(newCard);
    }

    public int calculateScore() {
        if (cards.stream().noneMatch(Card::isAce)) {
            return cards.stream()
                    .mapToInt(Card::score)
                    .sum();
        }
        return calculateScoreWithAce();
    }

    private int calculateScoreWithAce() {
        int minimumSum = cards.stream()
                .mapToInt(Card::score)
                .sum();
        int threshold = MAX_SCORE - (MAX_ACE_SCORE - MIN_ACE_SCORE);
        if (minimumSum <= threshold) {
            return minimumSum - MIN_ACE_SCORE + MAX_ACE_SCORE;
        }
        return minimumSum;
    }

    public boolean isBust() {
        return calculateScore() > 21;
    }

    public boolean isBlackJack() {
        return calculateScore() == MAX_SCORE && cards.size() == BLACKJACK_CARD_COUNT;
    }

    public boolean doesDealerNeedCard() {
        return calculateScore() <= DEALER_HIT_THRESHOLD;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CardHand cardHand = (CardHand) o;
        return Objects.equals(cards, cardHand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
