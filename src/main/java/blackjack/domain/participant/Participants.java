package blackjack.domain.participant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final List<Participant> participants;

    public Participants(String[] users) {
        this.participants = Arrays.stream(users)
                .map(User::new)
                .collect(Collectors.toList());
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public List<User> getUsers() {
        return participants.stream()
                .filter(Participant::isUser)
                .map(User.class::cast)
                .collect(Collectors.toList());
    }

    public List<String> getUserNames() { // user객체 안으로 이동
        return getUsers().stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    public Dealer getDealer() {
        return (Dealer) participants.stream()
                .filter(Participant::isDealer)
                .findAny()
                .orElseThrow(RuntimeException::new);
    }
}
