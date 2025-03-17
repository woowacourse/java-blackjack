package blackjack.gametable.gambler;

import blackjack.gametable.card.Card;
import blackjack.gametable.card.Cards;
import blackjack.constant.MatchResult;
import java.util.List;

public class Dealer extends Gambler {

    public static final int MAX_SCORE = 21;
    public static final int DEALER_HIT_THRESHOLD = 16;

    @Override
    public List<Card> openInitialCards() {
        return cards.openDealerInitialCards();
    }

    public Players updateBetAmounts(Players players) {
        List<Player> players1 = players.getPlayers();

        double dealerAmount = 0;

        for (Player player : players1) {
            MatchResult matchResult = compareTo(player);

            if (matchResult == MatchResult.BLACKJACK_WIN) {
                double betAmount = player.getBetAmount();
                double blackjackAmount = betAmount * 1.5;
                player.updateBetAmount(blackjackAmount);
                dealerAmount -= blackjackAmount;
                continue;
            }
            if (matchResult == MatchResult.WIN) {
                double betAmount = player.getBetAmount();
                dealerAmount -= betAmount;
            }
            if (matchResult == MatchResult.PUSH) {
                player.updateBetAmount(0);
                continue;
            }
            if (matchResult == MatchResult.LOSE) {
                double betAmount = player.getBetAmount();
                double playerAmount = betAmount * (-1);
                player.updateBetAmount(playerAmount);
                dealerAmount += playerAmount;
                continue;
            }
        }
        super.updateBetAmount(dealerAmount);
        return new Players(players1);
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
        return getMatchResult(dealerScore, playerScore);
    }

    private MatchResult getMatchResultWhenBlackjack(Cards playerCards) {
        if (playerCards.isBlackjack() && cards.isBlackjack()) {
            return MatchResult.PUSH;
        }
        if (playerCards.isBlackjack()) {
            return MatchResult.BLACKJACK_WIN;
        }
        return MatchResult.LOSE;
    }

    private MatchResult getMatchResultWhenOverBustStandard(int playerScore) {
        if (playerScore > MAX_SCORE) {
            return MatchResult.LOSE;
        }
        return MatchResult.WIN;
    }

    private MatchResult getMatchResult(int dealerScore, int playerScore) {
        if (playerScore == dealerScore) {
            return MatchResult.PUSH;
        }
        if (playerScore > dealerScore) {
            return MatchResult.WIN;
        }
        return MatchResult.LOSE;
    }

    public boolean isSumUnderThreshold() {
        return sumCardScores() <= DEALER_HIT_THRESHOLD;
    }
}
