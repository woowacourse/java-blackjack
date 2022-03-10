package blackjack.dto;

public class PlayerResultDTO {

    private final String name;
    private final boolean isWin;

    public PlayerResultDTO(String name, boolean isWin) {
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
