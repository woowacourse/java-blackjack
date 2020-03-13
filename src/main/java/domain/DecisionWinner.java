package domain;

import domain.player.Player;

public class DecisionWinner {
    private DecisionWinner() {
    }

    public static boolean compareWinner(Player targetPlayer, Player player) {
        int playerCardSum = CardCalculator.calculateDeterminedAce(targetPlayer.getCard());
        int dealerCardSum = CardCalculator.calculateDeterminedAce(player.getCard());

        return determineWin(playerCardSum, dealerCardSum);
    }

    private static boolean determineWin(int TargetPlayerCardSum, int playerCardSum) {
        if (TargetPlayerCardSum < 21 && playerCardSum > 21) {
            return true;
        }
        if (TargetPlayerCardSum > 21) {
            return false;
        }
        return TargetPlayerCardSum >= playerCardSum;
    }
}
