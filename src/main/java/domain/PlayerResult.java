package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.Arrays;

public enum PlayerResult {
    WIN("승") {
        @Override
        boolean isMatch(Dealer dealer, Player player) {
            return !player.isNotBust() && (dealer.isBust() || player.calculateScore() > dealer.calculateScore());
        }
    },
    DRAW("무") {
        @Override
        boolean isMatch(Dealer dealer, Player player) {
            return dealer.isNotBust() && !player.isBust() && player.calculateScore() == dealer.calculateScore();
        }
    },
    LOSE("패") {
        @Override
        boolean isMatch(Dealer dealer, Player player) {
            return player.isBust() || player.calculateScore() < dealer.calculateScore();
        }
    };

    private String name;

    PlayerResult(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract boolean isMatch(Dealer dealer, Player player);

    public static PlayerResult match(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(result -> result.isMatch(dealer, player))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("승무패 조건에 맞지 않습니다."));
    }
}
