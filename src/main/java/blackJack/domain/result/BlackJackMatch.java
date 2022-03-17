package blackJack.domain.result;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;

public enum BlackJackMatch {

    WIN_BLACKJACK("승", 1.5),
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1),
    LOSE_BLACK_JACK("패", -1.5);

    private final String result;
    private final double profitRatio;

    BlackJackMatch(String result, double profitRatio) {
        this.result = result;
        this.profitRatio = profitRatio;
    }

    public static double calculateProfitRatio(Player player, Dealer dealer) {
        final BlackJackMatch resultByBust = getWinLoseByBust(player, dealer);
        if (resultByBust != null) return resultByBust.profitRatio;

        final BlackJackMatch resultByBlackJack = getWinDrawByBlackJack(player, dealer);
        if (resultByBlackJack != null) return resultByBlackJack.profitRatio;

        return getWinDrawLoseByScore(player, dealer).profitRatio;
    }

    public static BlackJackMatch calculateMatch(Player player, Dealer dealer) {
        final BlackJackMatch resultByBust = getWinLoseByBust(player, dealer);
        if (resultByBust != null) return resultByBust;

        final BlackJackMatch resultByBlackJack = getWinDrawByBlackJack(player, dealer);
        if (resultByBlackJack != null) return resultByBlackJack;

        return getWinDrawLoseByScore(player, dealer);
    }

    private static BlackJackMatch getWinLoseByBust(Player player, Dealer dealer) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return null;
    }

    private static BlackJackMatch getWinDrawByBlackJack(Player player, Dealer dealer) {
        if (getWinByBlackJack(player, dealer)) {
            return WIN_BLACKJACK;
        }
        if (getDrawByBlackJack(player, dealer)) {
            return DRAW;
        }
        if (getLoseByBlackJack(player, dealer)) {
            return LOSE_BLACK_JACK;
        }
        return null;
    }

    private static boolean getWinByBlackJack(Player player, Dealer dealer) {
        return player.isBlackJack() && !dealer.isBlackJack();
    }

    private static boolean getDrawByBlackJack(Player player, Dealer dealer) {
        return player.isBlackJack() && dealer.isBlackJack();
    }

    private static boolean getLoseByBlackJack(Player player, Dealer dealer) {
        return !player.isBlackJack() && dealer.isBlackJack();
    }

    private static BlackJackMatch getWinDrawLoseByScore(Player player, Dealer dealer) {
        if (player.getScore() > dealer.getScore()) {
            return WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return DRAW;
        }
        return LOSE;
    }

    public static BlackJackMatch swapResult(BlackJackMatch result) {
        BlackJackMatch swapBlackJackResult = swapResultBlackJack(result);
        if (swapBlackJackResult != null) return swapBlackJackResult;

        if (result == WIN) {
            return LOSE;
        }
        if (result == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    private static BlackJackMatch swapResultBlackJack(BlackJackMatch result) {
        if (result == WIN_BLACKJACK) {
            return LOSE_BLACK_JACK;
        }
        if (result == LOSE_BLACK_JACK) {
            return WIN_BLACKJACK;
        }
        return null;
    }

    public double getProfitRatio() {
        return profitRatio;
    }

    public String getResult() {
        return result;
    }
}
