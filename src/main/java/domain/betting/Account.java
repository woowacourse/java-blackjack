package domain.betting;

import domain.participant.Player;

public class Account {

    private final Player player;
    private Money money;

    public Account(Player player, Money money) {
        this.player = player;
        this.money = money;
    }

    public void deposit(Money amount) {
        money = money.add(amount);
    }

    public void withdraw(Money amount) {
        money = money.subtract(amount);
    }

    public Money balance() {
        return money;
    }

    public Player getPlayer() {
        return player;
    }

    // TODO: setter 제거 -> 수익금에 대한 클래스 따로 만들기
    public void setMoney(Money money) {
        this.money = money;
    }
}
