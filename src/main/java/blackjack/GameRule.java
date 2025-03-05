package blackjack;

import java.util.List;

public class GameRule {
    private static final int DEALER_MUST_TAKE_UNDER = 16;
    private static final int PLAYER_MUST_TAKE_UNDER = 21;

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

}
