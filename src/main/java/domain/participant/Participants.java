package domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participants {

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants of(List<Player> players) {
        List<Participant> initialParticipants = new ArrayList<>(players);
        initialParticipants.add(new Dealer());
        return new Participants(initialParticipants);
    }

    public List<Player> getPlayers() {
        return participants.stream()
            .filter(participant -> participant instanceof Player)
            .map(participant -> (Player) participant)
            .toList();
    }

    public Dealer getDealer() {
        return (Dealer) participants.stream()
            .filter(participant -> participant instanceof Dealer)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("[ERROR] Dealer가 존재하지 않습니다."));
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
