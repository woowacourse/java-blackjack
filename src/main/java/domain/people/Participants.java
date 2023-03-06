package domain.people;

import java.util.ArrayList;
import java.util.List;

import view.ErrorMessage;

public class Participants {
    private static final int MINIMUM_PLAYER_COUNT = 1;
    private static final int MAXIMUM_PLAYER_COUNT = 7;
    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(List<String> names) {
        validate(names);
        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer());

        for (String name : names) {
            participants.add(new Player(name));
        }
        return new Participants(participants);
    }

    private static void validate(List<String> names) {
        validateNumberOfNames(names);
        validateNoDuplication(names);
    }

    private static void validateNumberOfNames(List<String> names) {
        if (names.size() < MINIMUM_PLAYER_COUNT || names.size() > MAXIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_OF_PLAYER.getMessage());
        }
    }

    private static void validateNoDuplication(List<String> names) {
        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException(ErrorMessage.NAME_IS_DUPLICATED.getMessage());
        }
    }

    public Dealer findDealer() {
        Participant dealer = participants.stream()
            .filter(participant -> participant.equals(new Dealer()))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException(ErrorMessage.NO_DEALER.getMessage())
            );
        return (Dealer)dealer;
    }

    public List<Player> findPlayers() {
        List<Player> players = new ArrayList<>();
        for (Participant participant : participants) {
            addParticipantIfPlayer(players, participant);
        }
        return players;
    }

    private void addParticipantIfPlayer(List<Player> players, Participant participant) {
        if (!participant.equals(new Dealer())) {
            players.add((Player)participant);
        }
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
