package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

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
    
    public List<Long> determinePlayersProfit(List<Player> players) {
        return players.stream()
                .map(this::determinePlayerProfit)
                .toList();
    }
    
    private long determinePlayerProfit(Player player) {
        if (player.isBlackjack()) {
            return playerBlackjackProfit(player);
        }
        if (isBust()) {
            return player.calculateProfit(ProfitRate.WIN);
        }
        if (player.isBust()) {
            return player.calculateProfit(ProfitRate.LOSE);
        }
        return player.calculateProfit(calculateProfitRate(getScore(), player.getScore()));
    }
    
    private long playerBlackjackProfit(Player player) {
        if (isBlackjack()) {
            return player.calculateProfit(ProfitRate.DRAW);
        }
        return player.calculateProfit(ProfitRate.WIN_BLACKJACK);
    }
    
    private ProfitRate calculateProfitRate(int dealerScore, int playerScore) {
        if (dealerScore == playerScore) {
            return ProfitRate.DRAW;
        }
        if (dealerScore < playerScore) {
            return ProfitRate.WIN;
        }
        return ProfitRate.LOSE;
    }
    
    public int determineProfit(List<Player> players) {
        List<Long> playersProfit = determinePlayersProfit(players);
        return playersProfit.stream()
                .mapToInt(Long::intValue)
                .sum();
    }
}
