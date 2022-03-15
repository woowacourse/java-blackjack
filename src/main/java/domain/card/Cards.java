package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final String ERROR_TO_CHECK_IF_BLACKJACK_FOR_INSUFFICIENT_CARDS = "카드가 두 장 미만인 경우, 블랙잭 여부확인이 불가합니다.";
    private static final int MAXIMUM_VALID_SCORE = 21;
    private static final int MINIMUM_CARDS_TO_CHECK_IF_BLACKJACK = 2;
    private static final int ALTERNATIVE_ACE_GAP = 10;
    private static final int CARD_QUANTITY_FOR_BLACKJACK = 2;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return calculateScore(cards) > MAXIMUM_VALID_SCORE;
    }

    public boolean isBlackJack() {
        if (cards.size() < MINIMUM_CARDS_TO_CHECK_IF_BLACKJACK) {
            throw new IllegalStateException(ERROR_TO_CHECK_IF_BLACKJACK_FOR_INSUFFICIENT_CARDS);
        }

        return getScore() == MAXIMUM_VALID_SCORE && cards.size() == CARD_QUANTITY_FOR_BLACKJACK;
    }

    public int getScore() {
        return calculateScore(cards);
    }

    private static int calculateScore(List<Card> cards) {
        if (containsAce(cards)) {
            return calculateScoreWithAce(cards);
        }

        return calculateScoreWithoutAce(cards);
    }

    private static boolean containsAce(List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private static int calculateScoreWithAce(List<Card> cards) {
        int minimumScore = calculateScoreWithoutAce(cards);

        if (minimumScore + ALTERNATIVE_ACE_GAP <= MAXIMUM_VALID_SCORE) {
            return minimumScore + ALTERNATIVE_ACE_GAP;
        }

        return minimumScore;
    }

    private static int calculateScoreWithoutAce(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
