package domain.result;

import domain.gamer.Gamer;

import java.util.Arrays;

public enum PlayerResult {
    BLACKJACK_WIN("블랙잭승", 1.5) {
        @Override
        boolean isMatch(Gamer dealer, Gamer player) {
            return (dealer.isNotBlackJack() && player.isBlackJack());
        }
    },

    WIN("승", 1) {
        @Override
        boolean isMatch(Gamer dealer, Gamer player) {
            return (player.isNotBust() && (dealer.isBust() || player.calculateScore().compareTo(dealer.calculateScore()) > 0))
                    && player.isNotBlackJack();
        }
    },
    DRAW("무", 0) {
        @Override
        boolean isMatch(Gamer dealer, Gamer player) {
            return (player.isNotBust() && player.isNotBlackJack() && dealer.isNotBlackJack() && player.calculateScore() == dealer.calculateScore())
                    || (dealer.isBlackJack() && player.isBlackJack());
        }
    },
    LOSE("패", -1) {
        @Override
        boolean isMatch(Gamer dealer, Gamer player) {
            return player.isBust()
                    || player.calculateScore().compareTo(dealer.calculateScore()) < 0
                    || (dealer.isBlackJack() && player.isNotBlackJack());
        }
    };

    private final String name;
    private final double earningRate;

    PlayerResult(String name, double earningRate) {
        this.name = name;
        this.earningRate = earningRate;
    }

    public static PlayerResult match(Gamer dealer, Gamer player) {
        return Arrays.stream(values())
                .filter(result -> result.isMatch(dealer, player))
                .findAny()
                .orElseThrow(() -> new NotFoundResultException("승무패 조건에 맞지 않습니다."));
    }

    public int multiply(int bettingMoney) {
        return (int) (bettingMoney * earningRate);
    }

    public String getName() {
        return name;
    }

    abstract boolean isMatch(Gamer dealer, Gamer player);
}
