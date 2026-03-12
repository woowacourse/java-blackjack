package domain.enums;

import domain.participant.Dealer;
import domain.participant.Player;

public enum Result {

    WIN("승"),
    WIN_BLACKJACK("블랙잭 승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    Result(String description) {
        this.description = description;
    }

    public static Result calculatePlayerResult(Dealer dealer, Player player) {
        if (isPlayerLose(dealer, player)) {
            return Result.LOSE;
        }
        if (isPlayerDraw(dealer, player)) {
            return Result.DRAW;
        }
        if (player.isBlackjack()) {
            return Result.WIN_BLACKJACK;
        }
        return Result.WIN;
    }

    private static boolean isPlayerLose(Dealer dealer, Player player) {
        if (player.isBust()) {
            return true;
        }
        return !dealer.isBust() && (player.getScore() < dealer.getScore());
    }

    private static boolean isPlayerDraw(Dealer dealer, Player player) {
        if (player.getScore() == dealer.getScore()) {
            return true;
        }
        return player.isBlackjack() && dealer.isBlackjack();
    }

    public static Result getOpposite(Result result) {
        if (result.equals(WIN) || result.equals(WIN_BLACKJACK)) {
            return LOSE;
        }
        if (result.equals(LOSE)) {
            return WIN;
        }
        return DRAW;
    }

    public String getDescription() {
        return description;
    }
}
