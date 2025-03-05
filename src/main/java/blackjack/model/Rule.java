package blackjack.model;

import java.util.function.Predicate;

public class Rule {

    private static final Predicate<Dealer> DEALER_DRAWABLE_SCORE = dealer -> dealer.getCards().sumAll() < 17;
    private static final Predicate<Player> PLAYER_DRAWABLE_SCORE = player -> player.getCards().sumAll() < 21;

    public boolean shouldDealerDraw(final Dealer dealer) {
        return DEALER_DRAWABLE_SCORE.test(dealer);
    }

    public boolean canPlayerDrewMoreCard(final Player player) {
        return PLAYER_DRAWABLE_SCORE.test(player);
    }
}
