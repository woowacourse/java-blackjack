package blackjack.domain;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participants {
    private final List<Person> people = new ArrayList<>();

    public Participants(Dealer dealer, List<Player> players) {
        this.people.add(dealer);
        this.people.addAll(players);
    }

    public List<Person> getPlayers() {
        return people.stream()
                .filter(Person::isPlayer)
                .collect(toList());
    }

    public Person getDealer() {
        return people.stream()
                .filter(Person::isDealer)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 딜러가 없습니다."));
    }

    public List<Person> getPeople() {
        return Collections.unmodifiableList(people);
    }
}
