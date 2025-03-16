package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;

public enum Winning {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    Winning(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Winning reverse(Winning winning) {
        if (winning == WIN) {
            return LOSE;
        }
        if (winning == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public static Winning determineForPlayer(Player player, Dealer dealer) {
        if (dealer.isBlackJack()) {
            return determinePushOrLose(player);
        }
        if (player.isBlackJack()) {
            return WIN;
        }
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return determineForPlayerByScore(player.calculateScore(), dealer.calculateScore());
    }

    private static Winning determinePushOrLose(Player player) {
        if (player.isBlackJack()) {
            return DRAW;
        }
        return LOSE;
    }

    private static Winning determineForPlayerByScore(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        return DRAW;
    }
}
