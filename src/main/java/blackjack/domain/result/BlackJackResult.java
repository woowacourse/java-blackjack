package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.Arrays;

public enum BlackJackResult implements BlackJackResultMatcher {

    BLACKJACK_WIN("블랙잭", 1.5) {
        @Override
        public boolean match(Dealer dealer, Player player) {
            return player.isBlackJack() && !dealer.isBlackJack();
        }
    },
    WIN("승", 1) {
        @Override
        public boolean match(Dealer dealer, Player player) {
            return player.handScore() > dealer.handScore();
        }
    },
    DRAW("무", 0) {
        @Override
        public boolean match(Dealer dealer, Player player) {
            return player.handScore() == dealer.handScore();
        }
    },
    LOSE("패", -1) {
        @Override
        public boolean match(Dealer dealer, Player player) {
            return player.handScore() < dealer.handScore();
        }
    };

    private final String koreanName;
    private final double profitRate;

    BlackJackResult(String koreanName, double profitRate) {
        this.koreanName = koreanName;
        this.profitRate = profitRate;
    }

    public static BlackJackResult findResult(Dealer dealer, Player player) {

        return Arrays.stream(BlackJackResult.values())
                .filter(result -> result.match(dealer, player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));
    }

    public BlackJackResult opposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public double getProfitRate() {
        return profitRate;
    }
}