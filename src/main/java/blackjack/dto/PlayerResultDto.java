package blackjack.dto;

public class PlayerResultDto {

    private final String name;
    private final boolean isWin;

    public PlayerResultDto(String name, boolean isWin) {
        this.name = name;
        this.isWin = isWin;
    }

    public String getName() {
        return name;
    }

    public boolean isWin() {
        return isWin;
    }
}
