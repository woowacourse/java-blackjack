package domain;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

public class ParticipantGenerator {

    private static final String DEALER_NAME = "딜러";

    private ParticipantGenerator() {
        throw new IllegalStateException("생성할 수 없는 객체입니다.");
    }

    public static Players createPlayers(final List<Name> names) {
        List<Card> emptyCards = new ArrayList<>();

        names.forEach(name -> validateNameIsNotDealer(name.getName()));

        return names.stream()
                .map(name -> new Player(name, new DrawnCards(emptyCards)))
                .collect(collectingAndThen(toList(), Players::new));
    }

    public static Dealer createDealer() {
        List<Card> emptyCards = new ArrayList<>();
        return new Dealer(new DrawnCards(emptyCards));
    }

    private static void validateNameIsNotDealer(final String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException("참여자의 이름은 '" + DEALER_NAME + "'가 되면 안됩니다.");
        }
    }
}
