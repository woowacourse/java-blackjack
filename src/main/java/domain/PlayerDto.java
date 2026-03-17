package domain;

public class PlayerDto {
    private final String name;
    private final int betAmount;

    public PlayerDto(String name, int betAmount) {
        this.name = name;
        this.betAmount = betAmount;
    }

    public String getName() {
        return name;
    }

    public int getBetAmount() {
        return betAmount;
    }
}
