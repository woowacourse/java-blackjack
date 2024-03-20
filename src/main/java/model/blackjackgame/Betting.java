package model.blackjackgame;

import model.participants.player.Player;

public class Betting {

    private static final int MIN_BETTING_MONEY = 0;

    private final Player player;
    private final int money;

    public Betting(Player player, int money) {
        validate(money);
        this.player = player;
        this.money = money;
    }

    private void validate(int money) {
        if (money <= MIN_BETTING_MONEY) {
            throw new IllegalArgumentException(MIN_BETTING_MONEY + "원 이하의 베팅은 하실 수 없습니다");
        }
    }

    public boolean isSamePlayerBetting(Player checkNeededplayer) {
        return player.getName().equals(checkNeededplayer.getName());
    }

    public Player getPlayer() {
        return player;
    }

    public int getMoney() {
        return money;
    }
}
