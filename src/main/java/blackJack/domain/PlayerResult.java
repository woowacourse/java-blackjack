package blackJack.domain;

import blackJack.domain.User.Dealer;
import blackJack.domain.User.Player;

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

    public static PlayerResult decision(Dealer dealer, Player player){
        if(isAllBlackJack(dealer, player) || dealer.isSameScore(player)){
            return PlayerResult.DRAW;
        }
        if(isLose(dealer, player)){
            return PlayerResult.WIN;
        }
        if(isWin(dealer, player)){
            return PlayerResult.LOSE;
        }
        throw new IllegalArgumentException("[ERROR] 없는 경우의 수입니다.");
    }

    private static boolean isLose(Dealer dealer, Player player) {
        return dealer.isBust() || (player.isGreaterScoreThan(dealer) && !player.isBust());
    }

    private static boolean isWin(Dealer dealer, Player player) {
        return player.isBust() || (dealer.isGreaterScoreThan(player) && !dealer.isBust());
    }

    private static boolean isAllBlackJack(Dealer dealer, Player player) {
        return dealer.isBlackJack() && player.isBlackJack();
    }

}
