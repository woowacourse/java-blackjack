package blackjack.domain;

import blackjack.domain.card.Cards;

public enum Result {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public static Result findResult(Cards myCards, Cards otherCards) {
        if (myCards.exceedMaxScore() && otherCards.exceedMaxScore()) {
            return DRAW;
        }
        if (myCards.exceedMaxScore()) {
            return LOSE;
        }
        if (otherCards.exceedMaxScore()) {
            return WIN;
        }
        return compareScore(myCards.calculateScore(myCards.getTotalScore(), myCards.getCountOfAce()),
                otherCards.calculateScore(otherCards.getTotalScore(), otherCards.getCountOfAce()));
    }

    private static Result compareScore(int myScore, int otherScore) {
        if (myScore > otherScore) {
            return WIN;
        }
        if (myScore < otherScore) {
            return LOSE;
        }
        return DRAW;
    }

    public String getResult() {
        return result;
    }
}
