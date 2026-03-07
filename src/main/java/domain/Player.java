package domain;

public class Player extends Participant {
    private final String name;

    public Player(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
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

    private GameResult getGameResult(int dealerScore) {
        if (calculateScore() > dealerScore) {
            return GameResult.WIN;
        }
        if (calculateScore() == dealerScore) {
            return GameResult.DRAW;
        }
        return GameResult.LOSE;
    }


}
