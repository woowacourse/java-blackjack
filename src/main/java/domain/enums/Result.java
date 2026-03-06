package domain.enums;

import domain.Dealer;
import domain.Participant;
import domain.Player;

public enum Result {

    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    UNDECIDED("미정");

    private final String description;

    Result(String description) {
        this.description = description;
    }

    public static Result calculateResult(Player player, Dealer dealer) {
        int playerScore = player.calculateScore();
        int dealerScore = dealer.calculateScore();

        if (player.isBurst() || (playerScore < dealerScore && !dealer.isBurst())) {
            return LOSE;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return WIN;
    }

    public static Result getOpposite(Result result) {
        if (result.equals(WIN)) {
            return LOSE;
        }

        if (result.equals(LOSE)) {
            return WIN;
        }

        return DRAW;
    }
}
