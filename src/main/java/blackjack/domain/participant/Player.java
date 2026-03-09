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
        return hand.getResultScore(BUSTED_SCORE) < BUSTED_SCORE;
    }

    public void stop() {
        stopDrawing = true;
    }

    public PlayerResult getWinningResult(int dealerScore) {
        int playerScore = getTotalScoreForResult();
        GameResult gameResult = getGameResult(dealerScore, playerScore);
        PlayerResult playerResult = new PlayerResult(player.getNickname(), gameResult);
        return null;
    }
}
