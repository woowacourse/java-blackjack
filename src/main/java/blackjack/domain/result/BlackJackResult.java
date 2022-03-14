package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.function.Predicate;

public enum BlackJackResult {

    BLACK_JACK(new BlackJack(), player -> player.showCards().size() == Card.START_CARD_COUNT &&
            player.calculateResult() == Gamer.LIMIT_GAMER_TOTAL_POINT),
    BUST(new Lose(), player -> player.calculateResult() > Gamer.LIMIT_GAMER_TOTAL_POINT),
    HIT(new Keep(), Player::isSatisfyReceiveCondition);

    private final Result result;
    private final Predicate<Player> predicate;

    BlackJackResult(final Result result, final Predicate<Player> predicate) {
        this.result = result;
        this.predicate = predicate;
    }

    public static BlackJackResult findBlackJackResult(final Player player) {
        return Arrays.stream(values())
                .filter(value -> value.predicate.test(player))
                .findFirst()
                .orElse(HIT);
    }

    public Result getResult() {
        return result;
    }
}
