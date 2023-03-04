package blackjack.model.card;

import blackjack.model.ResultState;

import java.util.List;

public class CardScore {

    private final List<CardNumber> numbers;
    private final ResultState state;

    public CardScore(List<CardNumber> numbers, ResultState state) {
        this.numbers = numbers;
        this.state = state;
    }

    public int smallScore() {
        int score = 0;
        for (CardNumber number : numbers) {
            score += number.getSmallScore();
        }
        return score;
    }

    public int bigScore() {
        int score = 0;
        for (CardNumber number : numbers) {
            score += number.getBigScore();
        }
        return score;
    }

    public int getValidScore() {
        if (bigScore() > 21) {
            return smallScore();
        }
        return bigScore();
    }

    public int compareTo(CardScore other) {
        if ((state == ResultState.STAND) && (state == (other.state))) {
            return (this.getValidScore() - other.getValidScore());
        }
        return state.getWinningScore() - other.state.getWinningScore();
    }

    @Override
    public String toString() {
        return "CardScore{" +
                "numbers=" + numbers +
                ", state=" + state +
                '}';
    }
}