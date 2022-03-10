package blackjack.domain.dto;

public class WinDrawLoseDto {
    private final String name;
    private final String winDrawLoseString;

    public WinDrawLoseDto(String name, String winDrawLoseString) {
        this.name = name;
        this.winDrawLoseString = winDrawLoseString;
    }

    public String getName() {
        return name;
    }

    public String getWinDrawLoseString() {
        return winDrawLoseString;
    }
}
