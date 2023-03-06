package blackjack.model.card;

import blackjack.model.ResultState;

public class CardScore {

    private final int score;
    private final ResultState state;

    public CardScore(int score, ResultState state) {
        this.score = score;
        this.state = state;
    }

    public int getScore() {
        return score;
    }

    public int compareTo(CardScore other) {
        if ((state == ResultState.STAND) && (state == (other.state))) {
            return (this.score - other.score);
        }
        return state.getWinningScore() - other.state.getWinningScore();
    }
}
