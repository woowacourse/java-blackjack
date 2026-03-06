package domain;

import java.util.List;

public class Player extends Participant{
    private final String name;

    public Player(String name, List<Card> holdCards) {
        super(holdCards);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public GameResult compareScore(int dealerScore) {
        if(isBust() && dealerScore > 21) {
            return GameResult.DRAW;
        }
        if(isBust() && dealerScore <= 21) {
            return GameResult.LOSE;
        }

        if(!isBust() && dealerScore > 21) {
            return GameResult.WIN;
        }
        if(calculateTotalScore() > dealerScore) {
            return GameResult.WIN;
        }
        if(calculateTotalScore() == dealerScore) {
            return GameResult.DRAW;
        }
        return GameResult.LOSE;
    }
}
