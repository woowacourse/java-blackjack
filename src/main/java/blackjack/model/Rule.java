package blackjack.model;

import java.util.Collections;
import java.util.function.Predicate;

public class Rule {

    private static final Predicate<Player> DEALER_DRAWABLE_SCORE = dealer ->
            Collections.min(dealer.calculateSumOfCards(), Integer::compareTo) < 17;
    private static final Predicate<Player> USER_DRAWABLE_SCORE = user ->
            Collections.min(user.calculateSumOfCards(), Integer::compareTo) < 21;

    public boolean canPlayerDrawMoreCard(final Player player) {
        if (player.hasRole(Role.DEALER)) {
            return DEALER_DRAWABLE_SCORE.test(player);
        }
        return USER_DRAWABLE_SCORE.test(player);
    }
}
