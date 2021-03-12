package blackjack.domain.participant;

public class Player extends Participant {
    private final GameResult gameResult = new GameResult();
    private Money money;

    public Player(String inputName) {
        this(new Name(inputName));
    }

    private Player(Name name) {
        super(name,2);
    }

    public void bet(Money money) {
        this.money = money;
    }

    public void fight(Dealer dealer) {
        if (isBust()) {
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
        if (dealer.isBust()) {
            gameResult.win();
            return;
        }
        if (this.cardResult() > dealer.cardResult()) {
            gameResult.win();
            return;
        }
        if (this.cardResult() < dealer.cardResult()) {
            gameResult.lose();
            return;
        }
        gameResult.draw();
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public double revenue() {
        return money.value() * profitRatio();
    }
}