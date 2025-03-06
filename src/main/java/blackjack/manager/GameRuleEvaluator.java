package blackjack.manager;

import blackjack.domain.CardHolder;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import java.util.List;

public class GameRuleEvaluator {
    private static final int DEALER_MUST_TAKE_UNDER = 16;
    private static final int PLAYER_MUST_TAKE_UNDER = 21;
    private static final int BUSTED_STANDARD_VALUE = 21;

    public boolean canTakeCardFor(Dealer dealer) {
        List<Integer> possibleSums = dealer.getPossibleSums();
        return possibleSums.stream()
                .allMatch(sum -> sum <= DEALER_MUST_TAKE_UNDER);
    }

    public boolean canTakeCardFor(Player player) {
        List<Integer> possibleSums = player.getPossibleSums();
        return possibleSums.stream()
                .anyMatch(sum -> sum <= PLAYER_MUST_TAKE_UNDER);
    }

    public boolean isBustedFor(Player player) {
        return isBusted(player.getCardHolder());
    }

    public boolean isBustedFor(Dealer dealer) {
        return isBusted(dealer.getCardHolder());
    }

    private boolean isBusted(CardHolder cardHolder) {
        List<Integer> possibleSums = cardHolder.getPossibleSums();

        return possibleSums.stream()
                .allMatch(sum -> sum > BUSTED_STANDARD_VALUE);
    }

}
