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
        return !hand.isBusted();
    }

    public void stop() {
        stopDrawing = true;
    }

    public PlayerResult determinePlayerResult(int dealerScore) {
        GameResult gameResult = resolveGameResult(dealerScore);
        return new PlayerResult(nickname, gameResult);
    }

    private GameResult resolveGameResult(int dealerScore) {
        if (hand.isBlackjack()) {
            return GameResult.WIN;
        }
        if (hand.isBusted()) {
            return GameResult.LOSE;
        }
        if (getTotalScore() < dealerScore) {
            return GameResult.LOSE;
        }
        return GameResult.WIN;
    }
}
