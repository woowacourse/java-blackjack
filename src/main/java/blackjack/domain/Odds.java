package blackjack.domain;

import blackjack.domain.participant.Participant;

import java.util.Arrays;

public enum Odds {
    BLACKJACK(true, true, 1.5), WIN(true, false, 1), DRAW(false, true, 0), LOSE(false, false, -1);

    private final boolean isWin;
    private final boolean isBlackJack;
    private final double odds;

    Odds(boolean isWin, boolean isBlackJack, double odds) {
        this.isWin = isWin;
        this.isBlackJack = isBlackJack;
        this.odds = odds;
    }

    public static double getOddsRatio(Participant player, Participant dealer) {
        return findOddsRatio(isWin(player, dealer), player.isBlackJack());
    }

    public static double findOddsRatio(boolean isWin, boolean isBlackJack) {
        return Arrays.stream(Odds.values()).filter(odd -> odd.isSameWin(isWin) && odd.isSameBlackJack(isBlackJack)).findFirst().orElse(LOSE).getOdds();
    }

    private static boolean isWin(Participant player, Participant dealer) {

        if (BlackJack.isOverMaxScore(player.getScore())) {
            return false;
        }
        if (BlackJack.isOverMaxScore(dealer.getScore())) {
            return true;
        }
        return dealer.getScore() < player.getScore();
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
