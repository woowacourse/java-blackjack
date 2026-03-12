package domain;


public class Player extends Participant{

    private final String name;
    private Amount amount;

    private GameResult gameResult;

    public Player(String name) {
        this.name = name;
    }

    public void bet(Amount amount) {
        this.amount = amount;
    }

    public int calculateProfit() {
        if (gameResult == GameResult.DRAW) {
            return 0;
        }
        if (gameResult == GameResult.WIN) {
            if (isBlackjack()) {
                return (int) (amount.amount() * 1.5);
            }
            return amount.amount();
        }
        return -amount.amount();
    }


    public String getName() {
        return name;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }
}
