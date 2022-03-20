package blackjack.dto;

public class PlayerCreateDto {
    private final String playerName;
    private final int betMoney;

    public PlayerCreateDto(String playerName, int betMoney) {
        this.playerName = playerName;
        this.betMoney = betMoney;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getBetMoney() {
        return betMoney;
    }
}
