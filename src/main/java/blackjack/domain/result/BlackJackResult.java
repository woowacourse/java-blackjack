package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

import static blackjack.domain.gamer.Gamer.MAX_CARD_VALUE;

public enum BlackJackResult {
    BLACK_JACK_WIN(1.5, ((player, dealer) -> player.isBlackJack() && !dealer.isBlackJack())),
    WIN(1, (player, dealer) ->
            ((player.isBlackJack() && !dealer.isBlackJack())
                    || (player.getCardsNumberSum() <= MAX_CARD_VALUE
                    && (player.getCardsNumberSum() > dealer.getCardsNumberSum() || dealer.getCardsNumberSum() > MAX_CARD_VALUE)))),
    LOSE(-1, (player, dealer) ->
            ((!player.isBlackJack() && dealer.isBlackJack())
                    || (player.getCardsNumberSum() > MAX_CARD_VALUE) || (dealer.getCardsNumberSum() <= MAX_CARD_VALUE
                    && player.getCardsNumberSum() < dealer.getCardsNumberSum()))),
    DRAW(0, (player, dealer) ->
            ((player.isBlackJack() && dealer.isBlackJack())
                    || (player.getCardsNumberSum() <= MAX_CARD_VALUE
                    && dealer.getCardsNumberSum() <= MAX_CARD_VALUE && player.getCardsNumberSum() == dealer.getCardsNumberSum())));

    private static final String NOT_EXIST_ERROR = "옯바른 결과를 찾을 수 없습니다.";
    private static final int REVERSE_VALUE = -1;

    private final double value; // 변수의 이름 다시 생각해보자.
    private final BiPredicate<Player, Dealer> predicate; // 변수 이름 다시 생각해보자.

    BlackJackResult(double value, BiPredicate<Player, Dealer> predicate) {
        this.value = value;
        this.predicate = predicate;
    }

    public static BlackJackResult of(Player point, Dealer otherPoint) {
        return Arrays.stream(values())
                .filter(result -> result.predicate.test(point, otherPoint))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_ERROR));
    }

    public static int getReverse(int value) {
        return value * REVERSE_VALUE;
    }

    public double getValue() {
        return value;
    }
}
