package blackjack.domain.result;

import java.util.List;

public enum ResultType {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private static final int BUST = 21;

    private final String word;

    ResultType(String word) {
        this.word = word;
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
}
