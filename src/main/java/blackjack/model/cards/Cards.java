package blackjack.model.cards;

import blackjack.vo.Score;
import java.util.ArrayList;
import java.util.List;

public class Cards {
    public static final int INITIAL_CARD_SIZE = 2;

    private final List<Card> cards;
    private Score score = new Score();

    public Cards() {
        this(new ArrayList<>());
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        updateCardsScore();
    }

    public void add(Card card) {
        cards.add(card);
        updateCardsScore();
    }

    public boolean isBust() {
        return score.isBust();
    }

    public boolean isBlackJack() {
        return cards.size() == INITIAL_CARD_SIZE && score.isBlackJack();
    }

    private void updateCardsScore() {
        int calculatedScore = calculateScore(cards);
        Score updatedScore = new Score(calculatedScore);
        if (hasAce() && updatedScore.lessThanWinningScoreWithExtraScore()) {
            updatedScore = Score.withExtraScore(calculatedScore);
        }
        score = updatedScore;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int calculateScore(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScoreValue() {
        return score.value();
    }
}
