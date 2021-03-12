package blackjack.domain;

import blackjack.domain.player.Player;

public class RevenueInfoDto {
    private final String name;
    private final int revenue;

    public RevenueInfoDto(final String name, final int revenue){
        this.name = name;
        this.revenue = revenue;
    }

    public RevenueInfoDto(final Player player){
        this(player.getNameValue(), player.getMoneyValue());
    }

    public String getName() {
        return name;
    }

    public int getRevenue() {
        return revenue;
    }
}
