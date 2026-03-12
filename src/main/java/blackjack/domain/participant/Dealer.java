package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    
    public Map<GameResult, Long> determineGameResult(List<Player> players) {
        return players.stream()
                .map(this::determinePlayerProfit)
                .collect(Collectors.groupingBy(
                        result -> result, Collectors.counting()
                ));
    }
    
    public int determineProfit(List<Player> players) {
        List<GameResult> gameResults = players.stream()
                .map(this::determinePlayerProfit)
                .toList();
    }
    
    public int determinePlayerProfit(Player player) {
        if (player.isBlackjack()) {
            return blackjackProfit(player);
        }
        if (isBust()) {
            return GameResult.LOSE;
        }
        if (player.isBust()) {
            return GameResult.WIN;
        }
        return compareScore(getScore(), player.getScore());
    }
    
    private int blackjackProfit(Player player) {
        if (isBlackjack()) {
            return 0;
        }
        return player.calculateProfit()
    }
    
    private GameResult compareScore(int score, int playerScore) {
        if (score == playerScore) {
            return GameResult.DRAW;
        }
        if (score < playerScore) {
            return GameResult.LOSE;
        }
        return GameResult.WIN;
    }
}
