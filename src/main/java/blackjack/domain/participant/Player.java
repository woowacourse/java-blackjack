package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.dto.PlayerResult;

public class Player extends Participant {
    
    private boolean stopDrawing;
    
    public Player(String nickname) {
        super(nickname);
        validate(nickname);
        stopDrawing = false;
    }
    
    private static void validate(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("플레이어 이름은 공백이 될 수 없습니다.");
        }
    }

    @Override
    public boolean isDrawable() {
        if (stopDrawing) {
            return false;
        }
        return hand.getDrawableScore(BUSTED_SCORE) < BUSTED_SCORE;
    }

    public void stop() {
        stopDrawing = true;
    }

    public PlayerResult determinePlayerResult(int dealerScore) {
        int playerScore = getResultScore();
        GameResult gameResult = resolveGameResult(playerScore, dealerScore);
        return new PlayerResult(nickname, gameResult);
    }

    private GameResult resolveGameResult(int playerScore, int dealerScore) {
        if (playerScore < dealerScore) {
            return GameResult.LOSE;
        }
        return GameResult.WIN;
    }

}
