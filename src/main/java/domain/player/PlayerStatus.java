package domain.player;

import domain.Result;
import domain.card.Card;
import java.util.List;

public class PlayerStatus {

    private final Hand hand;
    private final Bet bet;

    public PlayerStatus(Hand hand, Bet bet) {
        this.hand = hand;
        this.bet = bet;
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public void addHand(Card card) {
        hand.add(card);
    }

    public int getTotalScore() {
        return hand.getTotalScore();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public Result calculateResult(Dealer dealer) {
        if (hand.isBlackjack() && dealer.isBlackjack()) {
            return Result.DRAW;
        }

        if (hand.isBlackjack()) {
            return Result.WIN;
        }

        if (hand.isBust()) {
            return Result.LOSE;
        }

        if (dealer.isBust()) {
            return Result.WIN;
        }

        if (dealer.getTotalScore() > hand.getTotalScore()) {
            return Result.LOSE;
        }

        if (dealer.getTotalScore() == hand.getTotalScore()) {
            return Result.DRAW;
        }
        return Result.WIN;
    }

    public int calculateProfit(Dealer dealer) {
        Result result = calculateResult(dealer);
        if (hand.isBlackjack()) {
            return bet.blackjackWinProfit();
        }
        if (result == Result.WIN) {
            return bet.winProfit();
        }
        if (result == Result.DRAW) {
            return bet.refundProfit();
        }
        return bet.loseProfit();
    }

}
