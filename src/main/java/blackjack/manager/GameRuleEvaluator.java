package blackjack.manager;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.cardholder.CardHolder;
import java.util.List;

public class GameRuleEvaluator {

    public static final int BUSTED_STANDARD_VALUE = 21;
    private static final int DEALER_MUST_TAKE_UNDER = 16;

    public boolean canTakeCardFor(Dealer dealer) {
        List<Integer> possibleSums = dealer.getPossibleSums();
        return possibleSums.stream()
                .allMatch(sum -> sum <= DEALER_MUST_TAKE_UNDER);
    }

    public boolean canTakeCardFor(Player player) {
        List<Integer> possibleSums = player.getPossibleSums();
        return possibleSums.stream()
                .anyMatch(sum -> sum <= BUSTED_STANDARD_VALUE);
    }

    public boolean isBustedFor(Player player) {
        return isBustedFor(player.getCardHolder());
    }

    public boolean isBustedFor(Dealer dealer) {
        return isBustedFor(dealer.getCardHolder());
    }

    private boolean isBustedFor(CardHolder cardHolder) {
        List<Integer> possibleSums = cardHolder.calculatePossibleSums();

        return possibleSums.stream()
                .allMatch(sum -> sum > BUSTED_STANDARD_VALUE);
    }
}
