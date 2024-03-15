package model.blackjackgame;

import model.player.Player;

public class Betting {

    private static final int MIN_BETTING_MONEY = 0;
    private static final String BLACKJACK_WORD = "블랙잭";
    private static final String WIN_WORD = "승";
    private static final String FAIL_WORD = "패";
    private static final String DRAW_WORD = "무";

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

    public int profit(String result) {
        if (winOrDraw(result)) {
            return money;
        }
        if (fail(result)) {
            return -money;
        }
        if (blackjack(result)) {
            return (int) (money * 1.5);
        }
        return 0;
    }

    private boolean winOrDraw(String result) {
        return result.equals(WIN_WORD) || result.equals(DRAW_WORD);
    }

    private boolean fail(String result) {
        return result.equals(FAIL_WORD);
    }

    private boolean blackjack(String result) {
        return result.equals(BLACKJACK_WORD);
    }

    public Player getPlayer() {
        return player;
    }

    public int getMoney() {
        return money;
    }
}
