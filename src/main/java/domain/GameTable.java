package domain;

import java.util.ArrayList;
import java.util.List;

public class GameTable {

    private final List<Participant> participants;

    public GameTable() {
        this.participants = new ArrayList<>();

    }

    public void addPlayer(Participant player) {
        participants.add(player);
    }

}
