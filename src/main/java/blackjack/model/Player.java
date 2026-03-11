package blackjack.model;


import static blackjack.model.Hand.BLACKJACK_SCORE;

public class Player extends User {

    private GameResult gameResult;
    private final Bet bet;

    public Player(String name, int betAmount) {
        super(name);
        this.gameResult = GameResult.DRAW;
        this.bet = new Bet(betAmount);
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void mark(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public boolean canHit() {
        return getScore() < BLACKJACK_SCORE;
    }
}

