package domain;

import static domain.GameResult.DRAW;
import static domain.GameResult.LOSE;
import static domain.GameResult.WIN;

public class Player extends Gamer {
    private final String username;

    private static final int BUST_THRESHOLD = 21;

    public Player(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public boolean canGetMoreCard() {
        return this.canGetMoreCard(BUST_THRESHOLD);
    }

    public GameResult decideGameResult(Dealer dealer) {
        int playerScore = this.calculateScore();
        int dealerScore = dealer.calculateScore();

        if (dealerScore == playerScore) {
            return DRAW;
        }

        if (dealerScore > playerScore || playerScore > 21) {
            return LOSE;
        }
        return WIN;
    }
}
