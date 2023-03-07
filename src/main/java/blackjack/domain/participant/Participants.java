package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private static final String DEALER_NAME = "딜러";

    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants generate(List<String> playersName) {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer(new ParticipantName(DEALER_NAME)));
        for (String playerName : playersName) {
            participants.add(new Player(new ParticipantName(playerName)));
        }
        return new Participants(participants);
    }

    public List<Player> extractPlayers() {
        return participants.stream()
            .filter(participant -> participant.getClass() == Player.class)
            .map(participant -> (Player) participant)
            .collect(Collectors.toUnmodifiableList());
    }

    public Dealer extractDealer() {
        return (Dealer) participants.stream()
            .filter(participant -> participant.getClass() == Dealer.class)
            .findFirst()
            .get();
    }

    public List<String> getParticipantsName() {
        return participants.stream()
            .map(participant -> participant.getParticipantName().getName())
            .collect(Collectors.toList());
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
