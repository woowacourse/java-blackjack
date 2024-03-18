package domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participants {
    private static final int DEALER_POSITION = 0;

    private final List<Participant> participants;

    public Participants(List<Player> players) {
        this.participants = new ArrayList<>();
        participants.add(DEALER_POSITION, new Dealer());
        participants.addAll(players);
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public Dealer getDealer() {
        return (Dealer) participants.get(DEALER_POSITION);
    }

    public List<Player> getPlayers() {
        List<Participant> copiedParticipants = new ArrayList<>(participants);
        copiedParticipants.remove(DEALER_POSITION);

        return copiedParticipants.stream()
                .map(player -> (Player) player)
                .toList();
    }

    public List<String> getPlayerNames() {
        return getPlayers().stream()
                .map(Player::getName)
                .toList();
    }
}
