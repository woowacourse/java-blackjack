package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.participant.Participant;
import domain.participant.Player;

public class Players {
    private List<Player> players;

    public Players(String names) {
        List<Player> players = new ArrayList<>();
        String[] userNames = names.split(",");
        for (String name : userNames) {
            players.add(new Player(name));
        }
        this.players = players;
    }

    public List<String> getUserNames() {
        return players.stream().map(Participant::getName).collect(Collectors.toList());
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
