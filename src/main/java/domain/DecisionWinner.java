package domain;

import domain.player.Player;

public class DecisionWinner {
    private DecisionWinner() {
    }

    public static boolean compareWinner(Player player, Player dealer) {
        int playerCardSum = CardCalculator.calculateContainAce(player.getCard());
        int dealerCardSum = CardCalculator.calculateContainAce(dealer.getCard());

        return determineWin(playerCardSum, dealerCardSum);
    }

    private static boolean determineWin(int playerCardSum, int dealerCardSum) {
        if (playerCardSum < 21 && dealerCardSum > 21) {
            return true;
        }
        if (playerCardSum > 21) {
            return false;
        }
        return playerCardSum >= dealerCardSum;
    }
}
