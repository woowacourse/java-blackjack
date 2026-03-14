package blackjack.domain.participant;

import blackjack.domain.bet.Bet;
import blackjack.domain.bet.ProfitRate;
import blackjack.domain.card.Card;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class Dealer extends Participant {

    private static final String DEALER_NICKNAME = "딜러";
    private static final int DEALER_SCORE = 16;

    public Dealer() {
        super(DEALER_NICKNAME);
    }

    public Card getFirstCard() {
        return getCards().getFirst();
    }

    @Override
    public boolean isDrawable() {
        return getScore() <= DEALER_SCORE;
    }

    public long determineProfit(Set<Entry<Player, Bet>> playerBets) {
        long playersTotalProfit = playerBets.stream()
                .mapToLong(this::determinePlayerProfit)
                .sum();
        return -playersTotalProfit;
    }

    public List<Integer> determinePlayerProfits(Set<Entry<Player, Bet>> playerBets) {
        return playerBets.stream()
                .map(this::determinePlayerProfit)
                .toList();
    }

    private int determinePlayerProfit(Entry<Player, Bet> playerBet) {
        Player player = playerBet.getKey();
        Bet bet = playerBet.getValue();
        ProfitRate profitRate = calculateProfitRate(player);
        return bet.calculateProfit(profitRate);
    }

    private ProfitRate calculateProfitRate(Player player) {
        if (player.isStand()) {
            return calculateStandProfitRate(getScore(), player.getScore());
        }
        return calculateProfitRateAboveBlackjackScore(player);
    }

    private ProfitRate calculateProfitRateAboveBlackjackScore(Player player) {
        if (player.isBlackjack()) {
            return playerBlackjackProfitRate();
        }
        return ProfitRate.LOSE;
    }

    private ProfitRate playerBlackjackProfitRate() {
        if (isBlackjack()) {
            return ProfitRate.DRAW;
        }
        return ProfitRate.WIN_BLACKJACK;
    }

    private ProfitRate calculateStandProfitRate(int dealerScore, int playerScore) {
        if (dealerScore == playerScore) {
            return ProfitRate.DRAW;
        }
        if (dealerScore < playerScore) {
            return ProfitRate.WIN;
        }
        return ProfitRate.LOSE;
    }
}
