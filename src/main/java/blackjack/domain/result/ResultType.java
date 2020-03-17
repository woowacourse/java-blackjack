package blackjack.domain.result;

import java.util.List;

public enum ResultType {
    BLACKJACK("블랙잭", 1.5),
    WIN("승", 1),
    LOSE("패", -1),
    DRAW("무", 0);

    public static final int BUST = 21;

    private final String word;
    private final double profitRate;

    ResultType(String word, double profitRate) {
        this.word = word;
        this.profitRate = profitRate;
    }

    public static ResultType findResultByScore(int playerScore, int dealerScore) {
        if (playerScore > BUST) {
            return LOSE;
        }
        if (dealerScore > BUST) {
            return WIN;
        }
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSE;
    }

    public ResultType reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return this;
    }

    public int countSameResultType(List<PlayerResult> playerResults) {
        return (int) playerResults.stream()
                .filter(playerResult -> playerResult.hasSameResult(this))
                .count();
    }

    public String getWord() {
        return word;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
