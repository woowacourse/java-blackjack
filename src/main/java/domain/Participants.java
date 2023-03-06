package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(PlayerNames playerNames) {
        return new Participants(playerNames.getNames()
                .stream()
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    public void addDealer(Dealer dealer) {
        assertNotDealerExist();
        participants.add(dealer);
    }

    private void assertNotDealerExist() {
        if (getDealer() != null) {
            throw new IllegalStateException();
        }
    }

    public List<Participant> getPlayers() {
        return participants.stream()
                .filter(participant -> participant instanceof Player)
                .map(participant -> (Player) participant)
                .collect(Collectors.toList());
    }

    public Participant getDealer() {
        return participants.stream()
                .filter(participant -> participant instanceof Dealer)
                .findFirst()
                .orElse(null);
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
