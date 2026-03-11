package domain.model;

import static constant.ErrorMessage.INVALID_BETTING_VALUE;

public class PlayerBetting {

    private final Player player;
    private final int value;

    private PlayerBetting(Player player, int value) {
        this.player = player;
        this.value = value;
    }

    public static PlayerBetting of(Player player, int value) {
        validateValue(value);
        return new PlayerBetting(player, value);
    }

    public Player getPlayer() {
        return player;
    }

    public int getValue() {
        return value;
    }

    public boolean isSamePlayer(Player player) {
        return this.player == player;
    }

    public void applyBetting(Dealer dealer) {
        double playerProfit = player.applyBetting(value);
        dealer.applyBetting(playerProfit);
    }

    private static void validateValue(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(INVALID_BETTING_VALUE.getMessage());
        }
    }
}
