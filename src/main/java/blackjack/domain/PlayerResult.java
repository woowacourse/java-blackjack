package blackjack.domain;

import blackjack.domain.User.Dealer;
import blackjack.domain.User.Player;

public enum PlayerResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    PlayerResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PlayerResult decision(Dealer dealer, Player player) {
        if (isAllBlackJack(dealer, player) || dealer.isSameScore(player)) {
            return PlayerResult.DRAW;
        }
        if (isDealerLose(dealer, player)) {
            return PlayerResult.WIN;
        }
        return PlayerResult.LOSE;
    }

    private static boolean isDealerLose(Dealer dealer, Player player) {
        return player.isBlackJack() || dealer.isBust() || (player.isGreaterScoreThan(dealer) && !player.isBust());
    }

    private static boolean isAllBlackJack(Dealer dealer, Player player) {
        return dealer.isBlackJack() && player.isBlackJack();
    }

}
