package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Players {
    private List<Player> players = new ArrayList<>();
    public Players(String names) {
        for(String name : splitName(names)) {
            players.add(new Player(name));
        }
    }

    private  List<String> splitName(String names) {
        return Arrays.asList(names.split(","));
    }
}
