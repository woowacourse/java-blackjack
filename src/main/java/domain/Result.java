package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;

public enum Result {
    WIN("승"),
    LOSE("패"),
    TIE("무");

    private final String name;

    Result(final String name) {
        this.name = name;
    }

    public static Result getPlayerResultWith(final Player player, final Dealer dealer) {
        if (isPlayerLose(player, dealer)) {
            return Result.LOSE;
        }
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return Result.TIE;
        }
        if (isPlayerWin(player, dealer)) {
            return Result.WIN;
        }
        return compareScoreWith(player, dealer);
    }

    private static boolean isPlayerLose(final Player player, final Dealer dealer) {
        if (player.isBust() && dealer.isBust()) {
            return true;
        }
        if (!player.isBlackJack() && dealer.isBlackJack()) {
            return true;
        }
        if (player.isBust()) {
            return true;
        }
        return false;
    }

    private static boolean isPlayerWin(final Player player, final Dealer dealer) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return true;
        }
        if (dealer.isBust()) {
            return true;
        }
        return false;
    }

    private static Result compareScoreWith(final Player player, final Dealer dealer) {
        int playerScore = player.calculateTotalScore();
        int dealerScore = dealer.calculateTotalScore();
        if (playerScore > dealerScore && !player.isBust()) {
            return Result.WIN;
        }
        if (playerScore < dealerScore && !dealer.isBust()) {
            return Result.LOSE;
        }
        return Result.TIE;
    }

    public String getName() {
        return name;
    }
}
