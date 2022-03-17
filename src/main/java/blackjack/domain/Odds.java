package blackjack.domain;

import java.util.Arrays;

public enum Odds {
    ODDS_ON_BLACKJACK(true, true, 1.5),
    ODDS_ON_WIN(true, false, 1),
    ODDS_ON_DRAW(false, true, 0),
    ODDS_ON_LOSE(false, false, -1);

    private final boolean isWin;
    private final boolean isBlackJack;
    private final double odds;

    Odds(boolean isWin, boolean isBlackJack, double odds) {
        this.isWin = isWin;
        this.isBlackJack = isBlackJack;
        this.odds = odds;
    }

    public static double getOddsRatio(boolean isTargetWin, boolean isTargetBlackJack) {
        return Arrays.stream(Odds.values())
            .filter(odd -> odd.isSameWin(isTargetWin) && odd.isSameBlackJack(isTargetBlackJack))
            .findFirst()
            .orElse(ODDS_ON_LOSE)
            .getOdds();
    }

    private boolean isSameBlackJack(boolean isTargetBlackJack) {
        return isBlackJack == isTargetBlackJack;
    }

    private boolean isSameWin(boolean isTargetWin) {
        return isWin == isTargetWin;
    }

    private double getOdds() {
        return odds;
    }
}
