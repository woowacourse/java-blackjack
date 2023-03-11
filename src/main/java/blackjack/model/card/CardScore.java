package blackjack.model.card;

import blackjack.model.ResultState;
import blackjack.model.WinningResult;

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

    public WinningResult winningResult(CardScore other, Integer betting) {
        boolean isBlackjack = ResultState.BLACKJACK == this.state;
        if (this.compareTo(other) > 0) {
            return new WinningResult().win(betting, isBlackjack);
        }
        if (this.compareTo(other) < 0) {
            return new WinningResult().lose(betting);
        }
        return new WinningResult().draw(betting);
    }
}
