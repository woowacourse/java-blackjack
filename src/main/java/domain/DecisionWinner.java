package domain;

import domain.player.Player;

public class DecisionWinner {
    private DecisionWinner() {
    }

    public static boolean compareWinner(Player player1, Player player2) {
        return CardCalculator.calculateContainAce(player1.getCard())
                >= CardCalculator.calculateContainAce(player2.getCard());
    }
}
