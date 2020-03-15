package domain.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlayersName {
    private static final String DELIMITER = ",";

    private List<String> playerName;

    public PlayersName(String playerName) {
        this.playerName = new ArrayList<>(Arrays.asList(playerName.split(DELIMITER)));
        ;
    }

    public List<String> getPlayerName() {
        return Collections.unmodifiableList(playerName);
    }
}
