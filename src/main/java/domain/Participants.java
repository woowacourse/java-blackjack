package domain;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    List<Participant> participants;

    public Participants() {
        this.participants = new ArrayList<>();
    }

    public void add(Participant participant) {
        participants.add(participant);
    }

    public List<Participant> getPlayers() {
        return participants;
    }

    public List<String> getPlayerNames() {
        return participants.stream()
                .map(Participant::getName)
                .toList();
    }
}
