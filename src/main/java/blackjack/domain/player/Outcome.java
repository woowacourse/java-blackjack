package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.result.GameResult;
import java.util.List;

public class Outcome {
    private static final int MAXIMUM_SCORE = 21;
    private static final int DEALER_MINIMUM_SCORE = 17;
    public static final int CONVERTED_ACE_DIFFERENCE = 10;
    public static final int BLACK_JACK_CARDS_SIZE = 2;

    private final int cardsSize;
    private final int score;

    public Outcome(List<Card> cards) {
        this.score = calculateScore(cards);
        this.cardsSize = cards.size();
    }

    public boolean isBusted() {
        return score > MAXIMUM_SCORE;
    }

    public boolean isBlackJack() {
        return isMaxScore() && cardsSize == BLACK_JACK_CARDS_SIZE;
    }

    public boolean isMaxScore() {
        return score == MAXIMUM_SCORE;
    }

    public boolean isLessThanDealerMinimumScore() {
        return score < DEALER_MINIMUM_SCORE;
    }

    public GameResult compete(Outcome other) {
        if (isAllBlackJack(other) || isAllBusted(other)) {
            return GameResult.DRAW;
        }
        if (isWin(other)) {
            return GameResult.WIN;
        }
        if (isLose(other)) {
            return GameResult.LOSE;
        }
        return competeScore(other);
    }

    private int calculateScore(List<Card> cards) {
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        if (hasAce(cards)) {
            score = calculateScoreAceExists(cards, score);
        }
        return score;
    }

    private boolean hasAce(List<Card> cards) {
        return cards.stream().anyMatch(Card::isAce);
    }

    private int calculateScoreAceExists(List<Card> cards, int score) {
        int convertedAceAmount = 0;
        int currentBigAceAmount = (int) cards.stream().filter(Card::isAce).count();
        while (isBusted(score) && convertedAceAmount < currentBigAceAmount) {
            score -= CONVERTED_ACE_DIFFERENCE;
            convertedAceAmount++;
        }
        return score;
    }

    private boolean isBusted(int score) {
        return score > MAXIMUM_SCORE;
    }

    private boolean isAllBlackJack(Outcome other) {
        return this.isBlackJack() && other.isBlackJack();
    }

    private boolean isAllBusted(Outcome other) {
        return this.isBusted() && other.isBusted();
    }

    private boolean isWin(Outcome other) {
        if (this.isBlackJack()) {
            return true;
        }
        return other.isBusted();
    }

    private boolean isLose(Outcome other) {
        if (other.isBlackJack()) {
            return true;
        }
        return this.isBusted();
    }

    private GameResult competeScore(Outcome other) {
        if (this.score > other.score) {
            return GameResult.WIN;
        }
        if (this.score < other.score) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public int getScore() {
        return score;
    }

    public int getCardsSize() {
        return cardsSize;
    }
}
