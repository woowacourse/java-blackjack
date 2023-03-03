package blackjack.domain;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class People {
    private final List<Person> people = new ArrayList<>();

    public People(Dealer dealer, List<Player> players) {
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

    public Person findByName(String playerName) {
        return people.stream()
                .filter(person -> person.isNameMatch(playerName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 이름의 플레이어가 없습니다."));
    }
}
