package blackjack.domain.gamer;

public class Player extends BlackjackGamer {

    private static final int BLACKJACK_MAX_SCORE = 21;

    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    @Override
    public boolean canReceiveCard() {
        return getScore() <= BLACKJACK_MAX_SCORE;
    }

    public GameResult isWin(int dealerScore) {
        int playerScore = getScore();

        if (playerScore > BLACKJACK_MAX_SCORE) {
            return GameResult.LOSE;
        }
        if (dealerScore > BLACKJACK_MAX_SCORE || playerScore > dealerScore) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }

    public Name getName() {
        return name;
    }
}
