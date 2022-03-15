package blackJack.domain.card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cards {
    private static final int BLACK_JACK_CARD_COUNT = 2;
    private static final int BLACK_JACK = 21;
    private static final int ACE_BONUS_SCORE = 10;

    private final Set<Card> cards;

    public Cards() {
        cards = new HashSet<>();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_CARD_COUNT && calculateFinalScore() == BLACK_JACK;
    }

    public boolean isBust() {
        return calculateFinalScore() > BLACK_JACK;
    }

    public int calculateFinalScore() {
        final int score = calculateScore();
        if (hasAce() && checkValidationAceBonusScore(score)) {
            return score + ACE_BONUS_SCORE;
        }
        return score;
    }

    private boolean checkValidationAceBonusScore(int score) {
        return score + ACE_BONUS_SCORE <= BLACK_JACK;
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public List<Card> getCards() {
        return cards.stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
