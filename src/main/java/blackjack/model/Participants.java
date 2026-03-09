package blackjack.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Participants implements Iterable<Participant> {
    private static final String DELIMITER = ",";
    private static final String DEALER_NAME = "딜러";
    private static final int INCLUDE_EMPTY_ELEMENT = -1;

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(List<Player> players) {
        this.dealer = new Dealer(DEALER_NAME, new Hand());
        this.players = players;
    }

    public static Participants from(final String rawPlayerNames) {
        List<String> playerNames = Arrays.asList(rawPlayerNames.split(DELIMITER, INCLUDE_EMPTY_ELEMENT));
        validateDuplicatedNames(playerNames);
        List<Player> players = playerNames.stream()
            .map(playerName -> new Player(playerName.strip(), new Hand())).toList();
        return new Participants(players);
    }

    private static void validateDuplicatedNames(List<String> playerNames) {
        if (playerNames.contains(DEALER_NAME)) {
            throw new IllegalArgumentException("참가자의 이름은 딜러의 이름과 동일할 수 없습니다.");
        }
        if (playerNames.stream().map(String::strip).distinct().count() != playerNames.size()) {
            throw new IllegalArgumentException("참가자의 이름은 중복될 수 없습니다.");
        }
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Stream<Participant> stream() {
        return Stream.concat(java.util.stream.Stream.of(dealer), players.stream());
    }

    @Override
    public Iterator<Participant> iterator() {
        return stream().iterator();
    }
}
