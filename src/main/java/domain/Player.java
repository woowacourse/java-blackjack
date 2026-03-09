package domain;

import java.util.List;

public class Player extends Participant {
    private final String name;

    public Player(String name, List<Card> holdCards) {
        super(holdCards);
        validateEmptyNames(name);
        this.name = name;
    }

    private void validateEmptyNames(String playerName) {
        if (playerName.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 이름은 빈 값이 아니여야 합니다.");
        }
    }

    public GameResult compareScore(int dealerScore) {
        if (isBust() && dealerScore > 21) {
            return GameResult.DRAW;
        }
        if (isBust() && dealerScore <= 21) {
            return GameResult.LOSE;
        }
        if (!isBust() && dealerScore > 21) {
            return GameResult.WIN;
        }
        return getGameResult(dealerScore);
    }

    public String getName() {
        return name;
    }

    private GameResult getGameResult(int dealerScore) {
        if (calculateTotalScore() > dealerScore) {
            return GameResult.WIN;
        }
        if (calculateTotalScore() == dealerScore) {
            return GameResult.DRAW;
        }
        return GameResult.LOSE;
    }
}
