package blackjack.domain;

public enum PlayerOutcome {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private String value;

    PlayerOutcome(String value) {
        this.value = value;
    }

    public static PlayerOutcome match(int dealerTotal, int playerTotal) {
        if (dealerTotal > Blackjack.BLACKJACK_NUMBER && playerTotal > Blackjack.BLACKJACK_NUMBER) {
            return LOSE;
        }
        if (dealerTotal > Blackjack.BLACKJACK_NUMBER) {
            return WIN;
        }
        if (playerTotal > Blackjack.BLACKJACK_NUMBER) {
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

    public String getValue() {
        return this.value;
    }
}
