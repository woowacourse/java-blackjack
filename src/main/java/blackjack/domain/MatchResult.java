package blackjack.domain;

import java.util.Arrays;

public enum MatchResult {
    WIN("승") {
        @Override
        public boolean matches(Player player, Dealer dealer) {
            if (player.isBust()) {
                return false;
            }
            if (dealer.isBust()) {
                return true;
            }
            return player.score() > dealer.score();
        }
    },
    LOSE("패") {
        @Override
        public boolean matches(Player player, Dealer dealer) {
            if (player.isBust()) {
                return true;
            }
            if (dealer.isBust()) {
                return false;
            }
            return player.score() < dealer.score();
        }
    },
    DRAW("무") {
        @Override
        public boolean matches(Player player, Dealer dealer) {
            if (player.isBust() || dealer.isBust()) {
                return false;
            }
            return player.score() == dealer.score();
        }
    };

    private final String display;

    MatchResult(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public abstract boolean matches(Player player, Dealer dealer);

    public static MatchResult of(Player player, Dealer dealer) {
        return Arrays.stream(values())
                .filter(result -> result.matches(player, dealer))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("매칭되는 결과가 없습니다."));
    }
}

