package blackjack.domain.participant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer extends Participant {
    
    private static final String DEALER_NICKNAME = "딜러";
    private static final int DEALER_SCORE = 16;
    
    public Dealer() {
        super(DEALER_NICKNAME);
    }
    
    @Override
    public boolean isDrawable() {
        return hand.getTotalScore() <= DEALER_SCORE;
    }
    
    public Map<GameResult, Long> calculateResult(List<Player> players) {
        return players.stream()
                .map(this::calculateResult)
                .collect(Collectors.groupingBy(
                        result -> result, Collectors.counting()
                ));
    }
    
    public GameResult calculateResult(Player player) {
        if (player.isBusted()) {
            return GameResult.WIN;
        }
        if (isBusted()) {
            return GameResult.LOSE;
        }
        return compareScore(getScore(), player.getScore());
    }
    
    private GameResult compareScore(int score, int playerScore) {
        if (score < playerScore) {
            return GameResult.LOSE;
        }
        return GameResult.WIN;
    }
}
