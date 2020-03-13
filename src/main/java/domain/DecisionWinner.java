package domain;

import domain.card.CardCalculator;
import domain.player.Player;

public class DecisionWinner {

    private static final int BLACK_JACK = 21;

    private DecisionWinner() {
    }

    public static boolean compareWinner(Player targetPlayer, Player player) {
        int playerCardSum = CardCalculator.calculateDeterminedAce(targetPlayer.getCard());
        int dealerCardSum = CardCalculator.calculateDeterminedAce(player.getCard());

        return determineWin(playerCardSum, dealerCardSum);
    }

    private static boolean determineWin(int TargetPlayerCardSum, int playerCardSum) {
        if (TargetPlayerCardSum < BLACK_JACK && playerCardSum > BLACK_JACK) {
            return true;
        }
        if (TargetPlayerCardSum > BLACK_JACK) {
            return false;
        }
        return TargetPlayerCardSum >= playerCardSum;
    }
}
