package domain.game;

import domain.card.HandCards;

public enum Winning {
    BLACKJACK("블랙잭", 1.5),
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1),
    ;

    private final String name;
    private final double earningRate;

    Winning(String name, double earningRate) {
        this.name = name;
        this.earningRate = earningRate;
    }

    public static Winning determine(HandCards playerHands, HandCards dealerHands) {
        if(playerHands.isBlackJack() && dealerHands.isBlackJack()) {
            return DRAW;
        }
        if(playerHands.isBlackJack()) {
            return BLACKJACK;
        }
        if(dealerHands.isBlackJack()) {
            return LOSE;
        }

        if(playerHands.isBust()) {
            return LOSE;
        }
        if(dealerHands.isBust()) {
            return WIN;
        }

        return getWinningByScore(playerHands.calculateScore(), dealerHands.calculateScore());
    }

    private static Winning getWinningByScore(int playerScore, int dealerScore) {
        if(playerScore > dealerScore) {
            return WIN;
        }
        if(playerScore < dealerScore) {
            return LOSE;
        }
        return DRAW;
    }

    public String getName() {
        return name;
    }

    public double getEarningRate() {
        return earningRate;
    }
}
