package domain;

import domain.participant.Dealer;
import domain.participant.Player;

public enum PlayerGameResult {
    WIN("승"), DRAW("무"), LOSE("패");

    private final String value;

    PlayerGameResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PlayerGameResult from(Player player, Dealer dealer) {
        if (player.isBust() || dealer.isBust()) {
            return bustResult(player.isBust(), dealer.isBust());
        }
        return compareScore(player.calculateScore(), dealer.calculateScore());
    }

    private static PlayerGameResult bustResult(boolean playerIsBust, boolean dealerIsBust) {
        if (playerIsBust && dealerIsBust) {
            return DRAW;
        }
        if (dealerIsBust) {
            return WIN;
        }
        return LOSE;
    }

    private static PlayerGameResult compareScore(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSE;
    }
}
