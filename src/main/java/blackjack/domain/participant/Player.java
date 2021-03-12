package blackjack.domain.participant;

public class Player extends Participant {
    private final GameResult gameResult = new GameResult();
    private Money money;
    private double profitRatio;

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
            if (dealer.isBlackJack()) {
                gameResult.draw();
                return;
            }
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

    public void updateProfitRatio(Dealer dealer) {
        if (isDrawWith(dealer)) {
            profitRatio = 0;
            return;
        }
        if (isDefeatedBy(dealer)) {
            profitRatio = -1;
            return;
        }
        profitRatio = profitRatio();
    }

    public boolean isDrawWith(Dealer dealer) {
        return isBothBlackjack(dealer) || hasSameCardResultWith(dealer);
    }

    private boolean isBothBlackjack(Dealer dealer) {
        return isBlackJack() && dealer.isBlackJack();
    }

    private boolean hasSameCardResultWith(Dealer dealer) {
        return isBothStay(dealer) && cardResult() == dealer.cardResult();
    }

    private boolean isDefeatedBy(Dealer dealer) {
        return isBothStay(dealer) && cardResult() < dealer.cardResult();
    }

    private boolean isBothStay(Dealer dealer) {
        return isStay() && dealer.isStay();
    }

    public double revenue() {
        return money.value() * profitRatio;
    }
}