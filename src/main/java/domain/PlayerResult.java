package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.Arrays;

public enum PlayerResult {
    WIN("승") {
        @Override
        boolean isMatch(Dealer dealer, Player player) {
            return (player.isNotBust() && (dealer.isBust() || player.calculateScore().compareTo(dealer.calculateScore()) > 0))
                    || (dealer.isNotBlackJack() && player.isBlackJack());
        }
    },
    DRAW("무") {
        @Override
        boolean isMatch(Dealer dealer, Player player) {
            return (player.isNotBust() && player.isNotBlackJack() && dealer.isNotBlackJack() && player.calculateScore() == dealer.calculateScore())
                    || (dealer.isBlackJack() && player.isBlackJack());
        }
    },
    LOSE("패") {
        @Override
        boolean isMatch(Dealer dealer, Player player) {
            return player.isBust()
                    || player.calculateScore().compareTo(dealer.calculateScore()) < 0
                    || (dealer.isBlackJack() && player.isNotBlackJack());
        }
    };

    private final String name;

    PlayerResult(String name) {
        this.name = name;
    }

    public static PlayerResult match(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(result -> result.isMatch(dealer, player))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("승무패 조건에 맞지 않습니다."));
    }

    public String getName() {
        return name;
    }

    abstract boolean isMatch(Dealer dealer, Player player);
}
