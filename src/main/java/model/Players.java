package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private static final String DEALER = "딜러";

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players createDefault() {
        List<Player> players = new ArrayList<>();
        players.add(Player.from(Name.from(DEALER)));
        return new Players(players);
    }

    public void joinPlayer(String input) {
        List<Name> splitNames = Name.createSplitNames(input);

        players.addAll(splitNames.stream()
                .map(Player::from)
                .collect(Collectors.toList()));
    }

    public String getPlayerNameList() {
        List<Name> names = players.stream()
                .map(Player::getName)
                .filter(name -> !name.getName().equals(DEALER))
                .collect(Collectors.toList());

        return Name.chainingNames(names);
    }

    public String getDealer() {
        return DEALER;
    }
}
