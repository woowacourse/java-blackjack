package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

import static blackjack.domain.gamer.Gamer.MAX_CARD_VALUE;
import static blackjack.domain.gamer.Gamers.INIT_DISTRIBUTION_COUNT;


public enum BlackJackResult {
    WIN("승", (player, dealer) ->
            (isBlackJack(player) && !isBlackJack(dealer)
                    || player.getCardsNumberSum() <= MAX_CARD_VALUE
                    && (player.getCardsNumberSum() > dealer.getCardsNumberSum() || dealer.getCardsNumberSum() > MAX_CARD_VALUE))),
    LOSE("패", (player, dealer) ->
            (!isBlackJack(player) && isBlackJack(dealer)
                    || player.getCardsNumberSum() > MAX_CARD_VALUE) || (dealer.getCardsNumberSum() <= MAX_CARD_VALUE
                    && player.getCardsNumberSum() < dealer.getCardsNumberSum())),
    DRAW("무", (player, dealer) ->
            (isBlackJack(player) && isBlackJack(dealer)
                    || player.getCardsNumberSum() <= MAX_CARD_VALUE
                    && dealer.getCardsNumberSum() <= MAX_CARD_VALUE && player.getCardsNumberSum() == dealer.getCardsNumberSum()));

    private static final String NOT_EXIST_ERROR = "옯바른 결과를 찾을 수 없습니다.";

    private final String value;
    private final BiPredicate<Player, Dealer> predicate;

    BlackJackResult(String value, BiPredicate<Player, Dealer> predicate) {
        this.value = value;
        this.predicate = predicate;
    }

    public static BlackJackResult of(Player point, Dealer otherPoint) {
        return Arrays.stream(values())
                .filter(result -> result.predicate.test(point, otherPoint))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_ERROR));
    }

    private static boolean isBlackJack(Gamer gamer) {
        return gamer.getCardsNumberSum() == MAX_CARD_VALUE && gamer.getCardsSize() == INIT_DISTRIBUTION_COUNT;
    }

    public BlackJackResult getReverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getValue() {
        return value;
    }
}
