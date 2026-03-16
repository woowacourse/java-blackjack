package domain.model;

public class Player extends Person {

    private final String name;
    private PlayerStatus playerStatus = PlayerStatus.NONE;

    private Player(String name) {
        this.name = name;
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public String getName() {
        return name;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void changeStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    public double applyBetting(int bettingValue) {
        if (playerStatus == PlayerStatus.WIN) {
            if (super.isBlackJack()) {
                super.plusProfit((double) bettingValue * 3 / 2);
                return getProfit();
            }

            super.plusProfit(bettingValue);
            return getProfit();
        }

        if (playerStatus == PlayerStatus.LOSS) {
            super.minusProfit(bettingValue);
            return getProfit();
        }
        return getProfit();
    }

    @Override
    public boolean canAppend() {
        return getDeck().isAlive();
    }
}