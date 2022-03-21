package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.function.BiPredicate;

public enum BlackJackResult {
    BLACK_JACK_WIN(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0);

    public static final int MAX_CARD_VALUE = 21;
    private static final int REVERSE_VALUE = -1;

    private final double profitRate;

    BlackJackResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public static BlackJackResult findResult(Player player, Dealer dealer) {
        if (isPlayerBlackJackWin(player, dealer)) {
            return BLACK_JACK_WIN;
        }
        if (isPlayerWin(player, dealer)) {
            return WIN;
        }
        if (isPlayerLose(player, dealer)) {
            return LOSE;
        }
        return DRAW;
    }

    private static boolean isPlayerBlackJackWin(Player player, Dealer dealer) {
        return player.isBlackJack() && !dealer.isBlackJack();
    }

    private static boolean isPlayerWin(Player player, Dealer dealer) {
        return (player.isBlackJack() && !dealer.isBlackJack())
                || (player.getCardsNumberSum() <= MAX_CARD_VALUE
                && (player.getCardsNumberSum() > dealer.getCardsNumberSum() || dealer.getCardsNumberSum() > MAX_CARD_VALUE));
    }

    private static boolean isPlayerLose(Player player, Dealer dealer) {
        return (!player.isBlackJack() && dealer.isBlackJack())
                || (player.getCardsNumberSum() > MAX_CARD_VALUE) || (dealer.getCardsNumberSum() <= MAX_CARD_VALUE
                && player.getCardsNumberSum() < dealer.getCardsNumberSum());
    }

    public double getProfitRate() {
        return profitRate;
    }

    public static int getReverse(int value) {
        return value * REVERSE_VALUE;
    }

    public int getProfit(int value) {
        return (int) (this.profitRate * value);
    }
}
