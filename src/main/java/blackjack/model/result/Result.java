package blackjack.model.result;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;

public enum Result {
    WIN("승"),
    TIE("무"),
    LOSE("패");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public static Result checkPlayerResult(Player player, Dealer dealer) {
        if (isPlayerWin(player, dealer)) {
            return Result.WIN;
        }
        if (isPlayerLose(player, dealer)) {
            return Result.LOSE;
        }
        return Result.TIE;
    }

    private static boolean isPlayerWin(Player player, Dealer dealer) {
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return true;
        }
        if (player.isStand() && dealer.isBust()) {
            return true;
        }
        if (player.isStand() && dealer.isStand()) {
            return player.getScore() > dealer.getScore();
        }
        return false;
    }

    private static boolean isPlayerLose(Player player, Dealer dealer) {
        if (player.isBust()) {
            return true;
        }
        if (player.isStand() && dealer.isBlackjack()) {
            return true;
        }
        if (player.isStand() && dealer.isStand()) {
            return player.getScore() < dealer.getScore();
        }
        return false;
    }
}
