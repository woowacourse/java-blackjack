package blackjack.domain.card;

import blackjack.domain.Outcome;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserCards {

    private static final int BLACK_JACK_CARD_COUNT = 2;
    private static final int BLACK_JACK_SCORE = 21;

    private static final String DUPLICATE_CARD_EXCEPTION_MESSAGE = "카드가 중복되었습니다.";

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(DUPLICATE_CARD_EXCEPTION_MESSAGE);
        }
        cards.add(card);
    }

    private int getScore() {
        int score = cards.stream()
            .mapToInt(Card::getValue)
            .sum();
        if (canAddAceWeight(score) && hasAce()) {
            score += Symbol.ACE_WEIGHT;
        }
        return score;
    }

    private boolean canAddAceWeight(int score) {
        return score + Symbol.ACE_WEIGHT <= BLACK_JACK_SCORE;
    }

    private boolean hasAce() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return getScore() > BLACK_JACK_SCORE;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_CARD_COUNT && getScore() == BLACK_JACK_SCORE;
    }

    public int getTotalScore() {
        if (isBust()) {
            return 0;
        }
        return getScore();
    }

    public Outcome calculateOutcome(UserCards comparisonUserCards) {
        return Outcome.from(getTotalScore(), comparisonUserCards.getTotalScore());
    }

    public List<String> getInfos() {
        return cards.stream()
            .map(Card::toString)
            .collect(Collectors.toList());
    }

    public boolean isOverScore(int score) {
        return getScore() > score;
    }
}
