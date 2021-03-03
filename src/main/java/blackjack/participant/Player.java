package blackjack.participant;

import blackjack.Game;

public class Player extends Participant{
    private final Name name;
    private int winCount;
    private int drawCount;
    private int loseCount;

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
            loseCount++;
            return;
        }
        if (isBlackJack()) {
            winCount++;
            return;
        }
        compareValue(dealer);
    }

    private void compareValue(Dealer dealer) {
        if (this.calculateResult() > dealer.calculateResult()) {
            winCount++;
            return;
        }
        if (this.calculateResult() < dealer.calculateResult()) {
            loseCount++;
            return;
        }
        drawCount++;
    }

    @Override
    public String getName() {
        return name.toString();
    }

    public int getWinCount() {
        return winCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public int getLoseCount() {
        return loseCount;
    }
}