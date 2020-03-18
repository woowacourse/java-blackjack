package domain.gamer.dto;

import domain.gamer.Player;

public class GamerDto {
    private String name;
    private int battingMoney;

    public GamerDto(String name, int battingMoney) {
        this.name = name;
        this.battingMoney = battingMoney;
    }

    public static GamerDto of(Player player) {
        return new GamerDto(player.getName(), player.getBattingMoney());
    }

    public String getName() {
        return name;
    }

    public int getBattingMoney() {
        return battingMoney;
    }
}
