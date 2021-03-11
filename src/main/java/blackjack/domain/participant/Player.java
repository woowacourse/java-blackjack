package blackjack.domain.participant;

public class Player extends Participant {
    private final Name name;
    private final GameResult gameResult = new GameResult();

    public Player(String inputName) {
        this(new Name(inputName));
    }

    private Player(Name name) {
        this.name = name;
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


    @Override
    public String getName() {
        return name.toString();
    }

    @Override
    public int numberOfOpenCard() {
        return 2;
    }
}