package blackjack.domain;

import java.util.Arrays;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

public enum Score {
    WIN("승", 1.5) {
        @Override
        public boolean match(Player player, Dealer dealer) {
            return player.isBlackjack() && !dealer.isBlackjack();
        }
    },
    DRAW("무", 1) {
        @Override
        public boolean match(Player player, Dealer dealer) {
            if (player.isBlackjack() && dealer.isBlackjack()) {
                return true;
            }
            if (player.isBust()) {
                return false;
            }
            if (!dealer.isBust()) {
                return player.getTotalNumber() > dealer.getTotalNumber();
            }
            return true;

        }
    },
    LOSE("패", -1) {
        @Override
        public boolean match(Player player, Dealer dealer) {
            if (player.isBust()) {
                return true;
            }
            if (!player.isBlackjack() && !dealer.isBust()) {
                return player.getTotalNumber() <= dealer.getTotalNumber();
            }
            return false;
        }
    };

    private final String value;
    private final double dividendRate;


    Score(String value, double dividendRate) {
        this.value = value;
        this.dividendRate = dividendRate;
    }

    abstract public boolean match(Player player, Dealer dealer);

    public static Score compete(Player player, Dealer dealer) {
        return Arrays.stream(values())
            .filter(score -> score.match(player, dealer))
            .findAny().orElse(Score.LOSE);
    }

    public String getValue(){
        return value;
    }

    public double getDividendRate() {
        return dividendRate;
    }
}
