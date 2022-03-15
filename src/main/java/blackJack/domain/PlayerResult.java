package blackJack.domain;

import blackJack.domain.User.Dealer;
import blackJack.domain.User.Player;
import blackJack.domain.User.User;

public enum PlayerResult {
    WIN("승"),
    DRAW("무"),//PUSH
    LOSE("패");

    private final String value;

    PlayerResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PlayerResult decision(Dealer dealer, Player player){
        if(isBlackJack(dealer) && isBlackJack(player)){
            return PlayerResult.DRAW;
        }
        if(dealer.isLose(player)){
            return PlayerResult.WIN;
        }
        if(dealer.isWin(player)){
            return PlayerResult.LOSE;
        }
        throw new IllegalArgumentException("[ERROR] 없는 경우의 수입니다.");
    }

    private static boolean isBlackJack(User user) {
        return user.isBlackJack();
    }
}
