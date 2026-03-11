package domain;


import domain.participant.Dealer;
import domain.participant.Player;

public enum Result {
    WIN("승"), DRAW("무"), LOSE("패");

    private final String description;

    Result(String description) {
        this.description = description;
    }

    public static Result judge(Player player, Dealer dealer) {
        if (player.isBust() && dealer.isBust()) {
            return DRAW;
        }
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        Score playerScore = player.getTotalSum();
        Score dealerScore = dealer.getTotalSum();

        if (playerScore.isEqualTo(dealerScore)) {
            return DRAW;
        }
        if (playerScore.isGreaterThan(dealerScore)) {
            return WIN;
        }
        return LOSE;
    }

    public String getDescription() {
        return description;
    }
}
