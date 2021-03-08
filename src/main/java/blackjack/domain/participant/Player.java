package blackjack.domain.participant;

import blackjack.domain.Game;

public class Player extends Participant {
    private final Name name;

    public Player(String inputName) {
        name = new Name(inputName);
    }

    public boolean isBlackJack() {
        return calculateResult() == Game.BLACKJACK_NUMBER;
    }

    public void fight(Dealer dealer) {
        if (isBurst()) {
            gameResult.lose();
            dealer.gameResult.win();
            return;
        }
        if (isBlackJack()) {
            gameResult.win();
            dealer.gameResult.lose();
            return;
        }
        compareValue(dealer);
    }

    private void compareValue(Dealer dealer) {
        if (this.calculateResult() > dealer.calculateResult()) {
            gameResult.win();
            dealer.gameResult.lose();
            return;
        }
        if (this.calculateResult() < dealer.calculateResult()) {
            gameResult.lose();
            dealer.gameResult.win();
            return;
        }
        gameResult.draw();
        dealer.gameResult.draw();
    }

    @Override
    public String getName() {
        return name.toString();
    }

}