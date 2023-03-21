package domain.dto;

import domain.user.Name;

public class PrizeResultDto {
    private final String name;
    private final int prize;

    public PrizeResultDto(Name name, int prize) {
        this.name = name.getName();
        this.prize = prize;
    }

    public String getName() {
        return name;
    }

    public int getPrize() {
        return prize;
    }
}
