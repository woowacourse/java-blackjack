package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private static final int MAXIMUM_SCORE = 21;
    private static final int ACE_BONUS = 10;
    private static final int INIT_DRAW_COUNT = 2;

    private final List<Card> cards;

    public Cards() {
        this(new ArrayList<>());
    }

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public boolean isBlackJack() {
        return calculateTotalScore() == MAXIMUM_SCORE && cards.size() == INIT_DRAW_COUNT;
    }

    public int calculateTotalScore() {
        final int score = getTotalScore();

        if (isExistAce() && isScoreUpdatable(score)) {
            return score + ACE_BONUS;
        }

        return score;
    }

    private int getTotalScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean isExistAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private boolean isScoreUpdatable(final int score) {
        return score + ACE_BONUS <= MAXIMUM_SCORE;
    }

    public boolean isTotalScoreOver() {
        return calculateTotalScore() > MAXIMUM_SCORE;
    }

    public boolean isMaximumScore() {
        return calculateTotalScore() == MAXIMUM_SCORE;
    }

    public int count() {
        return cards.size();
    }

    public List<String> getCardInfos() {
        return cards.stream()
                .map(Card::getCardInfo)
                .collect(Collectors.toList());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
