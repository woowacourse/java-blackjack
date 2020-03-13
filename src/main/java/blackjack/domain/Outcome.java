package blackjack.domain;

import blackjack.util.BlackJackRule;

public enum Outcome {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    Outcome(String name) {
        this.name = name;
    }

    public static Outcome calculate(int playerScore, int dealerScore) {
        if (BlackJackRule.isBust(playerScore) || BlackJackRule.isBust(dealerScore)) {
            return BlackJackRule.isBust(playerScore) ? LOSE : WIN;
        }
        return (playerScore > dealerScore) ? WIN : ((playerScore == dealerScore) ? DRAW : LOSE);
    }

    public Outcome converse() {
        return (this == WIN) ? LOSE : ((this == DRAW) ? DRAW : WIN);
    }

    public String getName() {
        return name;
    }
}
