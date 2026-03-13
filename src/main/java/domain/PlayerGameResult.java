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
        return compareScore(player.getScore(), dealer.getScore());
    }


    private static PlayerGameResult compareScore(Score playerScore, Score dealerScore) {
        if (playerScore.compareTo(dealerScore) > 0) {
            return WIN;
        }
        if (playerScore.compareTo(dealerScore) == 0) {
            return DRAW;
        }
        return LOSE;
    }
}

