package domain.gamer.dto;

import domain.gamer.Dealer;
import domain.gamer.Player;

public class GamerMoneyDto {
    private String name;
    private int money;

    public GamerMoneyDto(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public static GamerMoneyDto of(Player player, int earning) {
        return new GamerMoneyDto(player.getName(), earning);
    }

    public static GamerMoneyDto of(Dealer dealer, int earning) {
        return new GamerMoneyDto(dealer.getName(), earning);
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }
}
