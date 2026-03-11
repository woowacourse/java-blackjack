package view;

import domain.pariticipant.Name;
import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public static List<Name> parsePlayers(String strPlayers) {

        String[] split = strPlayers.split(",", -1);

        List<Name> playerNames = new ArrayList<>();
        for (String name : split) {
            name = name.trim();

            playerNames.add(new Name(name));
        }

        return playerNames;
    }
}
