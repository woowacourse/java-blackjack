package domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final List<Participant> participants = new ArrayList<>();

    public Participants(Dealer dealer, Players players) {
        this.participants.add(dealer);
        this.participants.addAll(players.getPlayers());
    }

    public void initialTurn(){
        for (Participant participant : participants) {
            participant.hitInitialTurn();
        }
    }

    public List<String> toNames() {
        return participants.stream()
            .map(Participant::getName)
            .collect(Collectors.toList());
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
