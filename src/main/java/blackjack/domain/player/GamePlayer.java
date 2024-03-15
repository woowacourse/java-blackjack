package blackjack.domain.player;


import blackjack.domain.BettingAmount;
import blackjack.domain.Profit;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.ResultStatus;
import java.util.List;

public class GamePlayer extends Player {
    private static final Integer RECEIVE_SIZE = 21;

    private final BettingAmount battingAmount;

    public GamePlayer(Name name, Cards cards, BettingAmount battingAmount) {
        super(name, cards);
        this.battingAmount = battingAmount;
    }

    public Profit confirmProfit(Dealer dealer) {
        ResultStatus resultStatus = confirmResult(dealer);
        return battingAmount.calculateProfit(resultStatus);
    }

    private ResultStatus confirmResult(Dealer dealer) {
        int playerScore = calculateScore();
        int dealerScore = dealer.calculateScore();

        if (isBust()) {
            return ResultStatus.LOSE;
        }
        if (dealer.isBust()) {
            return ResultStatus.WIN;
        }
        if (isBlackjack() && !dealer.isBlackjack()) {
            return ResultStatus.BLACKJACK;
        }
        if (!isBlackjack() && dealer.isBlackjack()) {
            return ResultStatus.LOSE;
        }
        if (playerScore > dealerScore) {
            return ResultStatus.WIN;
        }
        if (playerScore == dealerScore) {
            return ResultStatus.DRAW;
        }
        return ResultStatus.LOSE;
    }

    @Override
    public List<Card> getOpenCards() {
        return getCards();
    }

    @Override
    public boolean isReceivable() {
        return cards.sum() < RECEIVE_SIZE && !isBlackjack();
    }

}
