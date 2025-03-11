package domain.participant;

public enum GameResult {
    BLACKJACK("블랙잭", 1.5),
    WIN("승", 1.0),
    DRAW("무", 0.0),
    LOSE("패", -1.0);

    private final String koreanName;
    private final double calculateValue;

    GameResult(String koreanName, double calculateValue) {
        this.koreanName = koreanName;
        this.calculateValue = calculateValue;
    }

    public static GameResult calculateResultOfDealer(Participant dealer, Participant player) {
        int dealerValue = dealer.getTotalValue();
        int playerValue = player.getTotalValue();

        boolean isDealerBlackJack = dealer.isBlackJack(dealerValue);
        boolean isPlayerBlackJack = player.isBlackJack(playerValue);
        if (isPlayerBlackJack && isDealerBlackJack) {
            return DRAW;
        }
        if (isPlayerBlackJack) {
            return BLACKJACK;
        }
        if (Participant.isBust(dealerValue) || Participant.isBust(playerValue)) {
            return calculateBurstResult(dealer, player);
        }
        return calculateResult(dealerValue, playerValue);
    }

    public double getCalculateValue(int amount) {
        return amount * this.calculateValue;
    }

    private static GameResult calculateBurstResult(Participant dealer, Participant player) {

        int dealerValue = dealer.getTotalValue();
        int playerValue = player.getTotalValue();
        if (Participant.isBust(dealerValue) && Participant.isBust(playerValue)) {
            return DRAW;
        }
        if (Participant.isBust(playerValue)) {
            return WIN;
        }
        return LOSE;
    }

    private static GameResult calculateResult(int dealerValue, int playerValue) {
        if (dealerValue > playerValue) {
            return WIN;
        }
        if (dealerValue < playerValue) {
            return LOSE;
        }
        return DRAW;
    }

    public GameResult convertByPlayerResult() {
        if (this == DRAW) {
            return DRAW;
        }
        if (this == BLACKJACK) {
            return BLACKJACK;
        }
        if (this == WIN) {
            return LOSE;
        }
        return WIN;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
