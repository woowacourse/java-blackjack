package blackjack.gametable;

import blackjack.constant.MatchResult;
import blackjack.gametable.card.Cards;
import blackjack.gametable.gambler.Dealer;
import blackjack.gametable.gambler.Player;

public class BlackjackRule {

    public static final int MAX_SCORE = 21;

    public static MatchResult evaluate(Dealer dealer, Player player) {
        int dealerScore = dealer.sumCardScores();
        int playerScore = player.sumCardScores();
        Cards playerCards = player.getCards();
        Cards dealerCards = dealer.getCards();

        if (dealerCards.isBlackjack() || playerCards.isBlackjack()) {
            return calculateMatchResultWhenBlackjack(dealerCards, playerCards);
        }
        if (dealerScore > MAX_SCORE || playerScore > MAX_SCORE) {
            return calculateMatchResultWhenOverBustStandard(playerScore);
        }
        return calculateMatchResult(dealerScore, playerScore);
    }

    private static MatchResult calculateMatchResultWhenBlackjack(Cards dealerCards, Cards playerCards) {
        if (playerCards.isBlackjack() && dealerCards.isBlackjack()) {
            return MatchResult.PUSH;
        }
        if (playerCards.isBlackjack()) {
            return MatchResult.BLACKJACK_WIN;
        }
        return MatchResult.LOSE;
    }

    private static MatchResult calculateMatchResultWhenOverBustStandard(int playerScore) {
        if (playerScore > MAX_SCORE) {
            return MatchResult.LOSE;
        }
        return MatchResult.WIN;
    }

    private static MatchResult calculateMatchResult(int dealerScore, int playerScore) {
        if (playerScore == dealerScore) {
            return MatchResult.PUSH;
        }
        if (playerScore > dealerScore) {
            return MatchResult.WIN;
        }
        return MatchResult.LOSE;
    }

}
