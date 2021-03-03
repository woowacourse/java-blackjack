package blackjack.participant;

import blackjack.Game;

public class Player extends Participant{
    private final Name name;
    private int winCount;
    private int drawCount;
    private int loseCount;
    private GameResult gameResult = new GameResult();

    public Player(String inputName) {
        this(new Name(inputName));
    }

    private Player(Name name) {
        this.name = name;
    }

    public boolean isBlackJack() {
        return calculateResult() == Game.BLACKJACK_NUMBER;
    }

    public void fight(Dealer dealer) {
        if (isBurst()) {
            gameResult.lose();
            return;
        }
        if (isBlackJack()) {
            gameResult.win();
            return;
        }
        compareValue(dealer);
    }

    private void compareValue(Dealer dealer) {
        if (this.calculateResult() > dealer.calculateResult()) {
            gameResult.win();
            return;
        }
        if (this.calculateResult() < dealer.calculateResult()) {
            gameResult.lose();
            return;
        }
        gameResult.draw();
    }

    @Override
    public String getName() {
        return name.toString();
    }

    public int getWinCount() {
        return gameResult.getWinCount();
    }

    public int getDrawCount() {
        return gameResult.getDrawCount();
    }

    public int getLoseCount() {
        return gameResult.getLoseCount();
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}