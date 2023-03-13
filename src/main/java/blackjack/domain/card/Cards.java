package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Cards {

    private static final int ACE_ADDITIONAL_SCORE = 10;
    private static final int MAX_SCORE = 21;
    public static final int BLACKJACK_CARD_COUNT_CONDITION = 2;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void takeCard(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        final int score = sumScore();

        if (hasAce() && isBurstAfterSumTen(score)) {
            return score + ACE_ADDITIONAL_SCORE;
        }
        return score;
    }

    private int sumScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(this::isAce);
    }

    public boolean isBust() {
        return calculateScore() > MAX_SCORE;
    }

    public boolean isBlackJack() {
        return calculateScore() == MAX_SCORE
                && cards.size() == BLACKJACK_CARD_COUNT_CONDITION;
    }

    private boolean isAce(final Card card) {
        return card.getNumber() == Number.ACE;
    }

    private boolean isBurstAfterSumTen(final int sum) {
        return sum + ACE_ADDITIONAL_SCORE <= MAX_SCORE;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public List<String> getCardNames() {
        return List.copyOf(cards.stream()
                .map(Card::getCardName)
                .collect(Collectors.toList()));
    }
}
