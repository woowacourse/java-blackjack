package blackjack.domain;

import blackjack.domain.gamer.Gamer;

public enum RoundResult {
    WIN("승"),
    LOSE("패"),
    TIE("무"),
    ;

    private final String displayName;

    RoundResult(String displayName) {
        this.displayName = displayName;
    }

    public static RoundResult judgeResult(Gamer gamer, Gamer otherGamer) {
        if (gamer.isBust() && otherGamer.isBust()) {
            return TIE;
        }
        if (gamer.isBust()) {
            return LOSE;
        }
        if (otherGamer.isBust()) {
            return WIN;
        }

        int gamerSumOfCards = gamer.getSumOfCards();
        int otherGamerSumOfCards = otherGamer.getSumOfCards();

        if (gamerSumOfCards > otherGamerSumOfCards) {
            return WIN;
        }
        if (gamerSumOfCards < otherGamerSumOfCards) {
            return LOSE;
        }

        if (gamer.isBlackjack() && otherGamer.isBlackjack()) {
            return TIE;
        }
        if (gamer.isBlackjack()) {
            return WIN;
        }
        if (otherGamer.isBlackjack()) {
            return LOSE;
        }
        return TIE;
    }

    public String getDisplayName() {
        return displayName;
    }
}
