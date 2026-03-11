package blackjack.model;


import static blackjack.model.Hand.BLACKJACK_SCORE;

public class Player extends User {

    private GameOutcome gameOutcome;
    private final Bet bet;

    public Player(String name, int betAmount) {
        super(name);
        this.gameOutcome = GameOutcome.DRAW;
        this.bet = new Bet(betAmount);
    }

    public Bet getBet() {
        return bet;
    }

    public GameOutcome getGameResult() {
        return gameOutcome;
    }

    public void mark(GameOutcome gameOutcome) {
        this.gameOutcome = gameOutcome;
    }

    public boolean canHit() {
        return getScore() < BLACKJACK_SCORE;
    }
}

