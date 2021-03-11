package blackjack.domain.result;

import static blackjack.domain.card.Cards.BLACKJACK_SCORE;

public enum MatchResultType {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String status;

    MatchResultType(String status) {
        this.status = status;
    }

    public static MatchResultType getMatchResultType(int dealerScore, int playerScore) {
        if (isDealerBusted(dealerScore) || (playerScore > dealerScore && isPlayerNotBusted(playerScore))) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSE;
    }

    private static boolean isDealerBusted(int dealerScore) {
        return dealerScore > BLACKJACK_SCORE;
    }

    private static boolean isPlayerNotBusted(int playerScore) {
        return playerScore <= BLACKJACK_SCORE;
    }

    @Override
    public String toString() {
        return status;
    }
}
