package blackjack.domain.result;

import blackjack.domain.gamer.BettingMoney;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.Arrays;

public enum BlackJackResult {

    BLACKJACK(1.5d, new BlackJackPolicy()),
    WIN(1d, new WinPolicy()),
    DRAW(0d, new DrawPolicy()),
    LOSE(-1d, new LosePolicy());

    private final double profitRate;
    private final ResultPolicy resultPolicy;

    BlackJackResult(double profitRate, ResultPolicy resultPolicy) {
        this.profitRate = profitRate;
        this.resultPolicy = resultPolicy;
    }

    public static BlackJackResult judge(Player player, Dealer dealer) {
        return Arrays.stream(BlackJackResult.values())
                .filter(result -> result.resultPolicy.judge(player, dealer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("결과가 없습니다."));
    }

    public Profit profit(BettingMoney bettingMoney) {
        return Profit.from(bettingMoney.getBettingMoney() * profitRate);
    }
}