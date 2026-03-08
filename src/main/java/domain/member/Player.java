package domain.member;

import domain.RoundResult;

public class Player extends Member {

    private static final int BUST_CONDITION = 21;

    public Player(String name) {
        super(name);
    }

    public RoundResult judgeAgainst(Dealer dealer) {
        int playerScore = this.hand.calculateTotalValue();
        int dealerScore = dealer.hand.calculateTotalValue();

        if (playerScore > BUST_CONDITION) return RoundResult.LOSE; // 내가 터지면 무조건 패
        if (dealerScore > BUST_CONDITION) return RoundResult.WIN;  // 나 안 터졌는데 딜러 터지면 승

        if (playerScore > dealerScore) return RoundResult.WIN;
        if (playerScore < dealerScore) return RoundResult.LOSE;
        return RoundResult.DRAW;
    }
}
