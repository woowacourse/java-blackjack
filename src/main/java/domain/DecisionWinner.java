package domain;

public class DecisionWinner {
    private DecisionWinner() {
    }

    public static boolean compareWinner(Player player1, Player player2) {
        return CardCalculator.calculateContainAce(player1) >= CardCalculator.calculateContainAce(player2);
    }
}
