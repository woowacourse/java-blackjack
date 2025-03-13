package domain.game;

import domain.card.Card;
import java.util.List;

public class Dealer extends Participant {

    public static final int DEALER_DRAW_BOUND = 16;

    private int totalProfit;
    private final BlackjackResultEvaluator blackjackResultEvaluator;

    public Dealer(BlackjackResultEvaluator blackjackResultEvaluator) {
        this.totalProfit = 0;
        this.blackjackResultEvaluator = blackjackResultEvaluator;
    }

    public void judgeGameResult(List<Player> players) {
        blackjackResultEvaluator.judgeGameResult(this, players);
    }

    public void calculateDealerProfit(Chip playerBettingResult) {
        this.totalProfit -= playerBettingResult.getBettingAmount();
    }

    public boolean isUnderDrawBound() {
        return calculateTotalCardNumber() <= DEALER_DRAW_BOUND;
    }

    public Card openSingleCard() {
        return cards.getFirst();
    }

    public int getTotalProfit() {
        return totalProfit;
    }
}
