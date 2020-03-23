package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.Arrays;

public enum BlackJackResult {

    BLACKJACK_WIN("블랙잭", 1.5, (dealer, player) -> player.isBlackJack() && !dealer.isBlackJack()),
    WIN("승", 1, (dealer, player) -> player.handScore() > dealer.handScore()),
    DRAW("무", 0, (dealer, player) -> !player.isBusted() && player.handScore() == dealer.handScore()),
    LOSE("패", -1, (dealer, player) -> player.isBusted() || player.handScore() < dealer.handScore());

    private final String koreanName;
    private final double profitRate;
    private final MatchStrategy matcher;

    BlackJackResult(String koreanName, double profitRate, MatchStrategy matcher) {
        this.koreanName = koreanName;
        this.profitRate = profitRate;
        this.matcher = matcher;
    }

    public static BlackJackResult findResult(Dealer dealer, Player player) {
        return Arrays.stream(BlackJackResult.values())
                .filter(result -> result.matcher.match(dealer, player))
                .findFirst()
                .orElseThrow(AssertionError::new);
    }

    public double getProfitRate() {
        return profitRate;
    }
}