package view;

import domain.participant.Name;
import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public static List<Name> parsePlayers(final String strPlayers) {

        final String[] split = strPlayers.split(",", -1);

        final List<Name> playerNames = new ArrayList<>();
        for (String name : split) {
            name = name.trim();

            playerNames.add(new Name(name));
        }

        return playerNames;
    }
}
