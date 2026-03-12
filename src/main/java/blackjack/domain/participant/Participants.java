package blackjack.domain.participant;

import blackjack.domain.result.Hand;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Participants {
    private static final String DELIMITER = ",";
    private static final Name DEALER_NAME = new Name("딜러");
    private static final int INCLUDE_EMPTY_ELEMENT = -1;

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(List<Player> players) {
        this.dealer = new Dealer(DEALER_NAME, new Hand());
        this.players = players;
    }

    public static Participants from(final String rawPlayerNames) {
        List<String> playerNames = Arrays.asList(
            rawPlayerNames.split(DELIMITER, INCLUDE_EMPTY_ELEMENT));
        validateDuplicatedNames(playerNames);

        List<Player> players = playerNames.stream()
            .map(playerName -> new Player(playerName, new Hand()))
            .toList();
        return new Participants(players);
    }

    private static void validateDuplicatedNames(List<String> playerNames) {
        if (playerNames.contains(DEALER_NAME.getCleaned())) {
            throw new IllegalArgumentException("참가자의 이름은 딜러의 이름과 동일할 수 없습니다.");
        }
        if (isDuplicated(playerNames)) {
            throw new IllegalArgumentException("참가자의 이름은 중복될 수 없습니다.");
        }
    }

    private static boolean isDuplicated(List<String> playerNames) {
        return playerNames.stream().map(String::strip).distinct().count() != playerNames.size();
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Stream<Participant> stream() {
        return Stream.concat(
            Stream.of(dealer),
            players.stream()
        );
    }
}
