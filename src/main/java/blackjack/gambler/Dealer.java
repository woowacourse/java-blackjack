package blackjack.gambler;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.constant.MatchResult;
import java.util.List;

public class Dealer extends Gambler {

    public static final int MAX_SCORE = 21;
    public static final int DEALER_HIT_THRESHOLD = 16;

    @Override
    public List<Card> openInitialCards() {
        return cards.openDealerInitialCards();
    }

    public MatchResult compareTo(Player player) {
        int dealerScore = sumCardScores();
        int playerScore = player.sumCardScores();
        Cards playerCards = player.getCards();

        if (cards.isBlackjack() || playerCards.isBlackjack()) {
            return getMatchResultWhenBlackjack(playerCards);
        }

        if (dealerScore > MAX_SCORE || playerScore > MAX_SCORE) {
            return getMatchResultWhenOverBustStandard(playerScore);
        }
        return getMatchResult(dealerScore, dealerScore);
    }

    private MatchResult getMatchResultWhenBlackjack(Cards playerCards) {
        if (playerCards.isBlackjack() && cards.isBlackjack()) {
            return MatchResult.DRAW;
        }
        if (playerCards.isBlackjack()) {
            return MatchResult.LOSE;
        }
        return MatchResult.WIN;
    }

    private MatchResult getMatchResultWhenOverBustStandard(int sum) {
        if (sum > MAX_SCORE) {
            return MatchResult.LOSE;
        }
        return MatchResult.WIN;
    }

    private MatchResult getMatchResult(int dealerScore, int sum) {
        if (sum == dealerScore) {
            return MatchResult.DRAW;
        }
        if (sum > dealerScore) {
            return MatchResult.WIN;
        }
        return MatchResult.LOSE;
    }

    public boolean isSumUnderThreshold() {
        return sumCardScores() <= DEALER_HIT_THRESHOLD;
    }
}
