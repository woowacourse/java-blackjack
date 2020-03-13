package domain;

import domain.player.Player;

public class DecisionWinner {
    private static final int BLACK_JACK = 21;

    private DecisionWinner() {
    }

    public static boolean compareWinner(Player player, Player dealer) {
        int playerCardSum = CardCalculator.calculateContainAce(player.getCard());
        int dealerCardSum = CardCalculator.calculateContainAce(dealer.getCard());

        return determineWin(playerCardSum, dealerCardSum);
    }

    private static boolean determineWin(int playerCardSum, int dealerCardSum) {
        if (playerCardSum < BLACK_JACK && dealerCardSum > BLACK_JACK) {
            return true;
        }
        if (playerCardSum > BLACK_JACK) {
            return false;
        }
        return playerCardSum >= dealerCardSum;
    }
}
