package model.blackjackgame;

import model.player.Player;

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
            throw new IllegalArgumentException("0원 이하의 베팅은 하실 수 없습니다");
        }
    }

    public int profit(String result) {
        if (result.equals("승")) {
            return money;
        }
        if (result.equals("패")) {
            return -money;
        }
        return 0;
    }

    public int getMoney() {
        return money;
    }
}
