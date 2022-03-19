package blackjack.domain.result;

import static blackjack.domain.BlackjackGame.BLACKJACK_NUMBER;

public enum PlayerOutcome {
    BLACKJACK_WIN("블랙잭 승", 1.5),
    WIN("승", 1),
    LOSE("패", -1),
    DRAW("무", 0);

    private String value;
    private double dividendRate;

    PlayerOutcome(String value, double dividendRate) {
        this.value = value;
        this.dividendRate = dividendRate;
    }

    // TODO 더 좋은 방법 찾기
    public static PlayerOutcome match(int dealerTotal, int playerTotal, int dealerCount, int playerCount) {
        if (dealerTotal == BLACKJACK_NUMBER && dealerCount == 2 && playerTotal == BLACKJACK_NUMBER && playerCount == 2) {
            return DRAW;
        }
        if (playerTotal == BLACKJACK_NUMBER && playerCount == 2) {
            return BLACKJACK_WIN;
        }
        return matchBlackJack(dealerTotal, playerTotal);
    }

    private static PlayerOutcome matchBlackJack(int dealerTotal, int playerTotal) {
        if (dealerTotal > BLACKJACK_NUMBER && playerTotal > BLACKJACK_NUMBER) {
            return LOSE;
        }
        if (dealerTotal > BLACKJACK_NUMBER) {
            return WIN;
        }
        if (playerTotal > BLACKJACK_NUMBER) {
            return LOSE;
        }
        return matchCards(dealerTotal, playerTotal);
    }

    private static PlayerOutcome matchCards(int dealerTotal, int playerTotal) {
        if (dealerTotal < playerTotal) {
            return WIN;
        }
        if (dealerTotal > playerTotal) {
            return LOSE;
        }
        return DRAW;
    }

    public double getDividendRate() {
        return dividendRate;
    }
}
