package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.function.Predicate;

public enum BlackJackResult {

    BLACK_JACK(new BlackJack(), player -> isBlackJack(player)),
    BUST(new Lose(), player -> isBust(player)),
    HIT(new Keep(), Player::isSatisfyReceiveCondition);

    private final ResultStrategy resultStrategy;

    private final Predicate<Player> predicate;

    BlackJackResult(final ResultStrategy resultStrategy, final Predicate<Player> predicate) {
        this.resultStrategy = resultStrategy;
        this.predicate = predicate;
    }
    public static BlackJackResult findBlackJackResult(final Player player) {
        return Arrays.stream(values())
                .filter(value -> value.predicate.test(player))
                .findFirst()
                .orElse(HIT);
    }

    private static boolean isBlackJack(Player player) {
        return player.showCards().size() == Card.START_CARD_COUNT &&
                player.calculateResult() == Gamer.LIMIT_GAMER_TOTAL_POINT;
    }

    private static boolean isBust(Player player) {
        return player.calculateResult() > Gamer.LIMIT_GAMER_TOTAL_POINT;
    }

    public ResultStrategy getResult() {
        return resultStrategy;
    }
}
