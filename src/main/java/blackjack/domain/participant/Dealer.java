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
        return hand.getCards().getFirst();
    }
    
    @Override
    public boolean isDrawable() {
        return hand.getTotalScore() <= DEALER_SCORE;
    }
    
    public Map<GameResult, Long> determineGameResult(List<Player> players) {
        return players.stream()
                .map(this::determineGameResult)
                .collect(Collectors.groupingBy(
                        result -> result, Collectors.counting()
                ));
    }
    
    public GameResult determineGameResult(Player player) {
        if (isBusted()) {
            return GameResult.LOSE;
        }
        if (player.isBusted()) {
            return GameResult.WIN;
        }
        return compareScore(getScore(), player.getScore());
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
