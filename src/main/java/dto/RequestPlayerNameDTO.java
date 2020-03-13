package dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestPlayerNameDTO {
    private static final String DELIMITER = ",";

    private String playerName;

    public RequestPlayerNameDTO(String playerName) {
        this.playerName = playerName;
    }

    public List<String> getPlayerName() {
        return new ArrayList<>(Arrays.asList(playerName.split(DELIMITER)));
    }
}
