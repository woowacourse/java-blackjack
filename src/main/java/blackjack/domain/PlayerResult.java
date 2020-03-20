package blackjack.domain;

import blackjack.domain.card.Cards;

public enum PlayerResult {
    BLACKJACK_WIN("승", 1.5),
    WIN("승", 1.0),
    LOSE("패", -1.0),
    DRAW("무", 0.0);

    private String koreanName;
    private double bettingRatio;

    PlayerResult(String koreanName, double bettingRatio) {
        this.koreanName = koreanName;
        this.bettingRatio = bettingRatio;
    }

    public static PlayerResult of(Cards dealerCards, Cards playerCards) {
        if (dealerCards.isBlackJack()) {
            return getResultWhenDealerIsBlackJack(playerCards);
        }
        if (dealerCards.isBust()) {
            return getResultWhenDealerIsBust(playerCards);
        }
        return getResultWhenDealerIsNeitherBlackJackNorBust(dealerCards, playerCards);
    }

    private static PlayerResult getResultWhenDealerIsBust(Cards playerCards) {
        if (playerCards.isBlackJack()) {
            return PlayerResult.BLACKJACK_WIN;
        }
        if (playerCards.isBust()) {
            return PlayerResult.LOSE;
        }
        return PlayerResult.WIN;
    }

    private static PlayerResult getResultWhenDealerIsBlackJack(Cards playerCards) {
        if (playerCards.isBlackJack()) {
            return PlayerResult.DRAW;
        }
        return PlayerResult.LOSE;
    }

    private static PlayerResult getResultWhenDealerIsNeitherBlackJackNorBust(Cards dealerCards, Cards playerCards) {
        if (playerCards.isBlackJack()) {
            return PlayerResult.BLACKJACK_WIN;
        }
        if (playerCards.isBust()) {
            return PlayerResult.LOSE;
        }
        if (dealerCards.getScore() < playerCards.getScore()) {
            return PlayerResult.WIN;
        }
        if (dealerCards.getScore() == playerCards.getScore()) {
            return PlayerResult.DRAW;
        }
        return PlayerResult.LOSE;
    }

    public int calculateProfit(int bettingMoney) {
        return (int) (this.bettingRatio * bettingMoney);
    }
}
