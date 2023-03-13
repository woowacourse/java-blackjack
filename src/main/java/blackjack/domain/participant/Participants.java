package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private static final String DEALER_NAME = "딜러";

    private final List<Participant> participants;

    private Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(final List<ParticipantName> playersName, final List<Amount> playersAmount, final Dealer dealer) {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        for (int index = 0; index < playersName.size(); index++) {
            participants.add(new Player(playersName.get(index), playersAmount.get(index)));
        }
        return new Participants(participants);
    }

    public List<Player> findPlayers() {
        return getParticipants().stream()
                .filter(participant -> !participant.getParticipantName().equals(new ParticipantName(DEALER_NAME)))
                .map(it -> (Player) it)
                .collect(Collectors.toList());
    }

    public Dealer findDealer() {
        return (Dealer) getParticipants().stream()
                .filter(participant -> participant.getParticipantName().equals(new ParticipantName(DEALER_NAME)))
                .findFirst()
                .get();
    }

    public List<String> findParticipantsName() {
        return participants.stream()
            .map(participant -> participant.getParticipantName().getName())
            .collect(Collectors.toUnmodifiableList());
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
