package domain.result;

import domain.gamer.Gamer;
import domain.result.rule.*;

import java.util.Arrays;

public enum PlayerResult {
    BLACKJACK_WIN("블랙잭승", 1.5, new BlackJackWinningRule()),
    WIN("승", 1, new WinningRule()),
    DRAW("무", 0, new DrawRule()),
    LOSE("패", -1, ((dealer, player) -> player.isBust()
            || player.calculateScore().compareTo(dealer.calculateScore()) < 0
            || (dealer.isBlackJack() && player.isNotBlackJack())));

    private final String name;
    private final double earningRate;
    private final MatchRule matchRule;

    PlayerResult(String name, double earningRate, MatchRule matchRule) {
        this.name = name;
        this.earningRate = earningRate;
        this.matchRule = matchRule;
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

    public boolean isMatch(Gamer dealer, Gamer player) {
        return matchRule.isMatch(dealer, player);
    }
}
