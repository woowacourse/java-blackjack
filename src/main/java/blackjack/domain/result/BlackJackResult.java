package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.function.Predicate;

public enum BlackJackResult {

    BLACK_JACK(player -> player.showCards().size() == Card.START_CARD_COUNT &&
            player.calculateResult() == Gamer.LIMIT_GAMER_TOTAL_POINT),
    BUST(player -> player.calculateResult() > Gamer.LIMIT_GAMER_TOTAL_POINT),
    HIT(Player::isSatisfyReceiveCondition);

    private final Predicate<Player> predicate;

    BlackJackResult(final Predicate<Player> predicate) {
        this.predicate = predicate;
    }

    public static BlackJackResult findBlackJackResult(final Player player) {
        return Arrays.stream(values())
                .filter(value -> value.predicate.test(player))
                .findFirst()
                .orElse(HIT);
    }
}
