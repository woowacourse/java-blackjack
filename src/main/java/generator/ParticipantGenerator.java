package generator;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import domain.Card;
import domain.Dealer;
import domain.DrawnCards;
import domain.Player;
import domain.Players;
import domain.Status;
import java.util.ArrayList;
import java.util.List;

public class ParticipantGenerator {

    private ParticipantGenerator() {
        throw new IllegalStateException("생성할 수 없는 객체입니다.");
    }

    public static Players createPlayers(final List<Status> statuses) {
        List<Card> emptyCards = new ArrayList<>();

        return statuses.stream()
                .map(status -> new Player(status, new DrawnCards(emptyCards)))
                .collect(collectingAndThen(toList(), Players::new));
    }

    public static Dealer createDealer() {
        List<Card> emptyCards = new ArrayList<>();
        return new Dealer(new DrawnCards(emptyCards));
    }
}
