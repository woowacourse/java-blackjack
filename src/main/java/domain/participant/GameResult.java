package domain.participant;

import domain.card.BlackJackRule;

public enum GameResult {
    WIN("승"), DRAW("무"), LOSE("패");

    private final String koreanName;

    GameResult(String koreanName) {
        this.koreanName = koreanName;
    }

    public static GameResult calculateDealerResult(int dealerValue, int playerValue) {
        if (BlackJackRule.isBurstBy(dealerValue) || BlackJackRule.isBurstBy(playerValue)) {
            return calculateBurstResult(dealerValue, playerValue);
        }
        return calculateResult(dealerValue, playerValue);
    }

    private static GameResult calculateBurstResult(int dealerValue, int playerValue) {
        if (BlackJackRule.isBurstBy(dealerValue) && BlackJackRule.isBurstBy(playerValue)) {
            return DRAW;
        }
        if (BlackJackRule.isBurstBy(playerValue)) {
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

    public GameResult reverse() {
        if (this == DRAW) {
            return DRAW;
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
