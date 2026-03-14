package domain;


public class Player extends Participant{

    private final String name;
    private final Amount amount;

    private static final double BLACKJACK_BONUS_MULTIPLIER = 1.5;

    private GameResult gameResult;

    public Player(String name, Amount amount) {
        this.name = name;
        this.amount = amount;
    }

    public int calculateProfit() {
        if (gameResult == GameResult.DRAW) {
            return 0;
        }
        if (gameResult == GameResult.WIN) {
            if (isBlackjack()) {
                return (int) (amount.amount() * BLACKJACK_BONUS_MULTIPLIER);
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
