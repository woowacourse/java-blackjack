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
        // 블랙잭일 때
        if (super.isBlackJack() && playerStatus == PlayerStatus.WIN) {
            super.plusProfit((double) bettingValue * 3 / 2);
        }

        // 블랙잭이 아니고 승일 때
        if (!super.isBlackJack() && playerStatus == PlayerStatus.WIN) {
            super.plusProfit(bettingValue);
        }

        // 패일때
        if (playerStatus == PlayerStatus.LOSS) {
            super.minusProfit(bettingValue);
        }
        return getProfit();
    }
}