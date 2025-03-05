package blackjack.model;

import java.util.function.Predicate;

public class Rule {

    private static final Predicate<Player> DEALER_DRAWABLE_SCORE = dealer -> dealer.getCards().sumAll() < 17;
    private static final Predicate<Player> USER_DRAWABLE_SCORE = user -> user.getCards().sumAll() < 21;

    public boolean canPlayerDrawMoreCard(final Player player) {
        if (player.hasRole(Role.DEALER)) {
            return DEALER_DRAWABLE_SCORE.test(player);
        }
        return USER_DRAWABLE_SCORE.test(player);
    }

}
