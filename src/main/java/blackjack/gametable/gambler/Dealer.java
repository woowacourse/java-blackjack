package blackjack.gametable.gambler;

import blackjack.constant.MatchResult;
import blackjack.gametable.card.Card;
import blackjack.gametable.card.Cards;
import java.util.List;

public class Dealer extends Gambler {

    public static final int MAX_SCORE = 21;
    public static final int DEALER_HIT_THRESHOLD = 16;

    @Override
    public List<Card> openInitialCards() {
        return cards.openDealerInitialCards();
    }

    public void updateBetAmounts(Players players) {
        double dealerAmount = 0;

        for (Player player : players.getPlayers()) {
            MatchResult matchResult = compareTo(player);
            dealerAmount += handleMatchResult(matchResult, player);
        }

        super.updateBetAmount(dealerAmount);
    }

    public boolean shouldDrawCard() {
        return sumCardScores() <= DEALER_HIT_THRESHOLD;
    }

    private MatchResult compareTo(Player player) {
        int dealerScore = sumCardScores();
        int playerScore = player.sumCardScores();
        Cards playerCards = player.getCards();

        if (cards.isBlackjack() || playerCards.isBlackjack()) {
            return calculateMatchResultWhenBlackjack(playerCards);
        }

        if (dealerScore > MAX_SCORE || playerScore > MAX_SCORE) {
            return calculateMatchResultWhenOverBustStandard(playerScore);
        }
        return calculateMatchResult(dealerScore, playerScore);
    }

    private double handleMatchResult(MatchResult matchResult, Player player) {
        double betAmount = player.getBetAmount();

        if (matchResult == MatchResult.BLACKJACK_WIN) {
            double blackjackAmount = betAmount * 1.5;
            player.updateBetAmount(blackjackAmount);
            return -blackjackAmount;
        }
        if (matchResult == MatchResult.WIN) {
            return -betAmount;
        }
        if (matchResult == MatchResult.LOSE) {
            player.updateBetAmount(-betAmount);
            return betAmount;
        }
        return 0;
    }

    private MatchResult calculateMatchResultWhenBlackjack(Cards playerCards) {
        if (playerCards.isBlackjack() && cards.isBlackjack()) {
            return MatchResult.PUSH;
        }
        if (playerCards.isBlackjack()) {
            return MatchResult.BLACKJACK_WIN;
        }
        return MatchResult.LOSE;
    }

    private MatchResult calculateMatchResultWhenOverBustStandard(int playerScore) {
        if (playerScore > MAX_SCORE) {
            return MatchResult.LOSE;
        }
        return MatchResult.WIN;
    }

    private MatchResult calculateMatchResult(int dealerScore, int playerScore) {
        if (playerScore == dealerScore) {
            return MatchResult.PUSH;
        }
        if (playerScore > dealerScore) {
            return MatchResult.WIN;
        }
        return MatchResult.LOSE;
    }

}
