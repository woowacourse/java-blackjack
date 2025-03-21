package bet;

import participant.Player;
import result.MatchResultType;

import java.util.Arrays;

public enum BettingRateType {
    BlackJack_WIN(true,MatchResultType.WIN,1.5),
    BlackJack_DRAW(true,MatchResultType.DRAW,0),
    WIN(false,MatchResultType.WIN,1),
    DRAW(false,MatchResultType.DRAW,0),
    LOSE(false,MatchResultType.LOSE,-1);

    private final boolean isBlackJack;
    private final MatchResultType matchResultType;
    private final double rate;

    BettingRateType(boolean isBlackJack, MatchResultType matchResultType, double rate) {
        this.isBlackJack = isBlackJack;
        this.matchResultType = matchResultType;
        this.rate = rate;
    }

    public static BettingRateType of(Player player, MatchResultType matchResultType) {
        return Arrays.stream(BettingRateType.values())
                .filter(rate -> rate.isSatisfiedCondition(player, matchResultType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("조건에 부합하는 배당률이 존재하지 않습니다."));
    }

    private boolean isSatisfiedCondition(Player player, MatchResultType matchResultType) {
        return matchResultType == this.matchResultType && player.isBlackJack() == isBlackJack;
    }

    public Money calculateProfitMoney(Money money) {
        return money.times(rate);
    }
}
