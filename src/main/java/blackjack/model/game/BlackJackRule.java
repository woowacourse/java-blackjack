package blackjack.model.game;

import java.util.function.Predicate;

import blackjack.model.player.Player;

public class BlackJackRule {

    private static final Predicate<Player> DEALER_DRAW_PREDICATE = dealer -> dealer.calculatePoint() < 17;
    private static final Predicate<Player> USER_DRAW_PREDICATE = user -> user.calculatePoint() < 21;

    public boolean canDrawMoreCard(final Player player) {
        if (player.isDealer()) {
            return DEALER_DRAW_PREDICATE.test(player);
        }
        return USER_DRAW_PREDICATE.test(player);
    }

}
