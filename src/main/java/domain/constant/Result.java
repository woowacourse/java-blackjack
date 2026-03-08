package domain.constant;

import domain.Dealer;
import domain.Player;

public enum Result {
    WIN("승"), DRAW("무"), LOSE("패");

    private String name;

    Result(String name) {
        this.name = name;
    }

    public static Result of(Dealer dealer, Player player) {
        if (player.isBust()) {
            return LOSE;
        }

        if (dealer.isBust()) {
            return WIN;
        }

        return DRAW;
    }

    public String getName() {
        return name + " ";
    }
}
