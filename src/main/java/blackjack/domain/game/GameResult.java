package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.exception.ErrorMessage;

import java.util.Arrays;

public enum GameResult {
    BLACKJACK("블랙잭", "1.5") {
        @Override
        public boolean isMatch(Player player, Dealer dealer) {
            return player.isBlackjack() && !dealer.isBlackjack();
        }
    },
    WIN("승", "1.0") {
        @Override
        public boolean isMatch(Player player, Dealer dealer) {
            if (dealer.isBust()) {
                return !player.isBust();
            }
            return !player.isBust() && player.getScore().isGreaterThan(dealer.getScore());
        }
    },
    LOSE("패", "-1.0") {
        @Override
        public boolean isMatch(Player player, Dealer dealer) {
            if (player.isBust()) {
                return true;
            }
            if (dealer.isBlackjack()) {
                return !player.isBlackjack();
            }
            return dealer.getScore().isGreaterThan(player.getScore());
        }
    },
    DRAW("무", "0.0") {
        @Override
        public boolean isMatch(Player player, Dealer dealer) {
            if (player.isBlackjack() && dealer.isBlackjack()) {
                return true;
            }
            return !player.isBust() && !dealer.isBust()
                    && player.getScore().isSame(dealer.getScore());
        }
    };

    private final String status;
    private final String ratio;

    GameResult(String status, String ratio) {
        this.status = status;
        this.ratio = ratio;
    }

    public abstract boolean isMatch(Player player, Dealer dealer);

    public static GameResult matchResult(Player player, Dealer dealer) {
        return Arrays.stream(GameResult.values())
                .filter(gameResult -> gameResult.isMatch(player, dealer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_MATCH.getMessage()));
    }

    public String getRatio() {
        return ratio;
    }
}
