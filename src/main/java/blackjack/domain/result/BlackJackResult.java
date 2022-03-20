package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum BlackJackResult {
    BLACK_JACK_WIN(1.5, BlackJackResult::isPlayerBlackJackWin),
    WIN(1, BlackJackResult::isPlayerWin),
    LOSE(-1, BlackJackResult::isPlayerLose),
    DRAW(0, BlackJackResult::isPlayerDraw);

    public static final int MAX_CARD_VALUE = 21;
    private static final String NOT_EXIST_ERROR = "옯바른 결과를 찾을 수 없습니다.";
    private static final int REVERSE_VALUE = -1;

    private final double profitRate;
    private final BiPredicate<Player, Dealer> determine;

    BlackJackResult(double profitRate, BiPredicate<Player, Dealer> determine) {
        this.profitRate = profitRate;
        this.determine = determine;
    }

    public static BlackJackResult of(Player point, Dealer otherPoint) {
        return Arrays.stream(values())
                .filter(result -> result.determine.test(point, otherPoint))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_ERROR));
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

    private static boolean isPlayerDraw(Player player, Dealer dealer) {
        return (player.isBlackJack() && dealer.isBlackJack())
                || player.getCardsNumberSum() <= MAX_CARD_VALUE
                && dealer.getCardsNumberSum() <= MAX_CARD_VALUE && player.getCardsNumberSum() == dealer.getCardsNumberSum();
    }

    public double getProfitRate() {
        return profitRate;
    }

    public static int getReverse(int value) {
        return value * REVERSE_VALUE;
    }
}
