package blackjackgame.domain.blackjack;

public class CardResultCalculator {

    private CardResultCalculator() {
    }

    public static CardResult calculate(HoldingCards holdingCards) {
        if (holdingCards.noAdditionalCard() && holdingCards.isSummationSameMaximum()) {
            return CardResult.BLACKJACK;
        }
        if (holdingCards.isSummationExceedMaximum()) {
            return CardResult.BUST;
        }
        return CardResult.NORMAL;
    }
}
