package blackjack.util;

import blackjack.domain.Player;
import java.util.ArrayList;
import java.util.List;

public class InputParser {

    private InputParser() {
    }

    public static List<Player> createUser(String userName) {
        String[] userNames = userName.split(",");

        List<Player> players = new ArrayList<>();
        for (String name : userNames) {
            players.add(new Player(name));
        }

        return players;
    }

}
