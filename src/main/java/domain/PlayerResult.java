package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.Arrays;

public enum PlayerResult {
    WIN("승") {
        @Override
        boolean isMatch(Dealer dealer, Player player) {
            return !player.isBust() && (dealer.isBust() || player.calculateScore() > dealer.calculateScore());
        }
    },
    DRAW("무") {
        @Override
        boolean isMatch(Dealer dealer, Player player) {
            return !dealer.isBust() && !player.isBust() && player.calculateScore() == dealer.calculateScore();
        }
    },
    LOSE("패") {
        @Override
        boolean isMatch(Dealer dealer, Player player) {
            return player.isBust() || player.calculateScore() < dealer.calculateScore();
        }
    };

    private String resultState;

    PlayerResult(String resultState) {
        this.resultState = resultState;
    }

    abstract boolean isMatch(Dealer dealer, Player player);

    public static PlayerResult match(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(result -> result.isMatch(dealer, player))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("조건이 아무래도 이상합니다."));
    }
}
