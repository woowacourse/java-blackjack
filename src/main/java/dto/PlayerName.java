package dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerName {
    private static final String DELIMITER = ",";

    private String playerName;

    public PlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<String> getPlayerName(){
        return new ArrayList<>(Arrays.asList(playerName.split(DELIMITER)));
    }
}
