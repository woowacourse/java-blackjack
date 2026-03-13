package domain;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.status.Blackjack;
import domain.status.Bust;
import domain.status.Status;

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
        return compareScore(player, dealer);
    }


    private static PlayerGameResult compareScore(Player player, Dealer dealer) {
        Status playerStatus = player.getStatus();
        Status dealerStatus = dealer.getStatus();
        if (playerStatus instanceof Blackjack && dealerStatus instanceof Blackjack) {
            return DRAW;
        }
        if (playerStatus instanceof Blackjack) {
            return WIN;
        }
        if (dealerStatus instanceof Blackjack) {
            return LOSE;
        }

        if (playerStatus instanceof Bust && dealerStatus instanceof Bust) {
            return DRAW;
        }
        if (dealerStatus instanceof Bust) {
            return WIN;
        }
        if (playerStatus instanceof Bust) {
            return LOSE;
        }
        if(player.getScore() > dealer.getScore()) {
            return WIN;
        }
        if(player.getScore() == dealer.getScore()) {
            return DRAW;
        }
        return LOSE;
    }
}

