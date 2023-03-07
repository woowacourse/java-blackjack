package blackjack.domain;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participants {
    private final List<Person> participants = new ArrayList<>();

    public Participants(Dealer dealer, List<Player> players) {
        this.participants.add(dealer);
        this.participants.addAll(players);
    }

    public List<Person> getPlayers() {
        return participants.stream()
                .filter(Person::isPlayer)
                .collect(toList());
    }

    public Person getDealer() {
        return participants.stream()
                .filter(Person::isDealer)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 딜러가 없습니다."));
    }

    public Person findByName(String name) {
        return participants.stream()
                .filter(person -> person.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 이름과 일치하는 참가자가 없습니다."));
    }

    public List<Person> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
