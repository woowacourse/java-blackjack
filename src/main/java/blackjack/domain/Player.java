package blackjack.domain;

public class Player extends Participant {

    private static final int MAXIMUM_SCORE_LIMIT = 21;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isAbleToReceiveCard() {
        int currentScore = calculateScoreWhenAceIsMinimum();
        return currentScore < MAXIMUM_SCORE_LIMIT;
    }

    public boolean isWin(Dealer dealer) {
        return super.getFinalScore() > dealer.getFinalScore();
    }
}
