package dto;

public class RequestPlayerInformationDTO {
    private String playerName;
    private int money;

    public RequestPlayerInformationDTO(String playerName, int money) {
        this.playerName = playerName;
        this.money = money;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public int getMoney() {
        return this.money;
    }
}
