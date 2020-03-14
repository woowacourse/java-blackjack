package dto;

import java.util.*;

public class RequestPlayerNamesDTO {
    private List<String> playerNames;

    public RequestPlayerNamesDTO(List<String> playerNames) {
        this.playerNames = playerNames;
    }

    public List<String> getPlayerName() {
        return Collections.unmodifiableList(this.playerNames);
    }
}
