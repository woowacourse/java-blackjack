package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;

public enum PlayerResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private static final String ERROR = "조건이 아무래도 이상합니다.";
    private String resultState;

    PlayerResult(String resultState) {
        this.resultState = resultState;
    }

    public String getResultState() {
        return resultState;
    }

    public static PlayerResult match(Dealer dealer, Player player) {
        if(!player.isBust() && dealer.isBust() || player.calculateScore() > dealer.calculateScore()) {
            return PlayerResult.WIN;
        } else if(!dealer.isBust() && !player.isBust() && player.calculateScore() == dealer.calculateScore()) {
            return PlayerResult.DRAW;
        }
        return PlayerResult.LOSE;
    }
}
