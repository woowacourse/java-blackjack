package dto;

public class RequestPlayerNameDTO {
    private String playerName;

    public RequestPlayerNameDTO(String playerName) {
        this.playerName = playerName;
    }

    public String[] getPlayerName(){
        return playerName.split(",");
    }
}
