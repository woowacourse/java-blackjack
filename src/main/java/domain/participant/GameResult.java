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

    public static GameResult calculateResultOfPlayer(Participant dealer, Participant player) {
        boolean isDealerBlackJack = dealer.isBlackJack();
        boolean isPlayerBlackJack = player.isBlackJack();
        if (isPlayerBlackJack && isDealerBlackJack) {
            return DRAW;
        }
        if (isPlayerBlackJack) {
            return BLACKJACK;
        }
        return calculateNoneBlackJackCase(dealer, player);
    }

    private static GameResult calculateNoneBlackJackCase(Participant dealer, Participant player) {
        int dealerValue = dealer.getTotalValue();
        int playerValue = player.getTotalValue();
        if (dealer.isBust(dealerValue) || player.isBust(playerValue)) {
            return calculateBurstResult(dealer, player);
        }
        return calculateResult(dealerValue, playerValue);
    }

    public int getCalculateValue(int amount) {
        return (int) (amount * this.calculateValue);
    }

    private static GameResult calculateBurstResult(Participant dealer, Participant player) {
        int dealerValue = dealer.getTotalValue();
        int playerValue = player.getTotalValue();
        if (dealer.isBust(dealerValue) && player.isBust(playerValue)) {
            return DRAW;
        }
        if (dealer.isBust(dealerValue)) {
            return WIN;
        }
        return LOSE;
    }

    private static GameResult calculateResult(int dealerValue, int playerValue) {
        if (dealerValue > playerValue) {
            return LOSE;
        }
        if (dealerValue < playerValue) {
            return WIN;
        }
        return DRAW;
    }

    public GameResult convertOfDealer() {
        if (this == DRAW) {
            return DRAW;
        }
        // 플레어어가 블랙잭인 경우, 딜러는 패배한다.
        if (this == BLACKJACK) {
            return LOSE;
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
