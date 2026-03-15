package domain.player;

import domain.card.Card;
import domain.game.Result;
import java.util.List;

public class BettingHand {
    private final Hand hand;
    private final BettingProfit bettingProfit;

    private BettingHand(Hand hand, BettingProfit bettingProfit) {
        this.hand = hand;
        this.bettingProfit = bettingProfit;
    }

    public static BettingHand of(Hand hand, BettingProfit bettingProfit) {
        return new BettingHand(hand, bettingProfit);
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public List<Card> cards() {
        return hand.getCards();
    }

    public int totalScore() {
        return hand.calculateTotalScore();
    }

    public int calculateProfit(Dealer dealer) {
        if (isBlackjackWin(dealer)) {
            return bettingProfit.blackjackWinProfit();
        }
        return bettingProfit.profit(determineResult(dealer));
    }

    public Result determineResult(Dealer dealer) {
        if (hand.isBust()) {
            return Result.LOSE;
        }
        if (hand.isBlackjack() && dealer.isBlackjack()) {
            return Result.DRAW;
        }
        if (dealer.isBust()) {
            return Result.WIN;
        }
        if (hand.calculateTotalScore() > dealer.totalScore()) {
            return Result.WIN;
        }
        if (hand.calculateTotalScore() == dealer.totalScore()) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    private boolean isBlackjackWin(Dealer dealer) {
        return hand.isBlackjack() && !dealer.isBlackjack();
    }
}
