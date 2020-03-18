package domain.gamer.dto;

import domain.gamer.Dealer;
import domain.gamer.Player;

public class GamerWithMoneyDto {
    private String name;
    private int money;

    public GamerWithMoneyDto(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public static GamerWithMoneyDto of(Player player, int earning) {
        return new GamerWithMoneyDto(player.getName(), earning);
    }

    public static GamerWithMoneyDto of(Dealer dealer, int earning) {
        return new GamerWithMoneyDto(dealer.getName(), earning);
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }
}
