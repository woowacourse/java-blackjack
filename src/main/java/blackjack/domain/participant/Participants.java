package blackjack.domain.participant;

import blackjack.domain.card.Hand;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Participants {
    private static final String DELIMITER = ",";
    private static final int INCLUDE_EMPTY_ELEMENT = -1;

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Participants(List<Player> players) {
        this(Dealer.create(), players);
    }

    public static Participants from(final String rawPlayerNames) {
        List<Player> players = parsePlayersFrom(rawPlayerNames);
        return new Participants(players);
    }

    private static List<Player> parsePlayersFrom(String rawPlayerNames) {
        List<Name> playerNames = Arrays.stream(
                rawPlayerNames.split(DELIMITER, INCLUDE_EMPTY_ELEMENT))
            .map(Name::new)
            .toList();
        validateDuplicatedNames(playerNames);

        return parsePlayersFrom(playerNames);
    }

    private static void validateDuplicatedNames(List<Name> playerNames) {
        if (containsDealerName(playerNames)) {
            throw new IllegalArgumentException("플레이어의 이름은 딜러의 이름과 동일할 수 없습니다.");
        }
        if (isDuplicated(playerNames)) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    private static boolean containsDealerName(List<Name> playerNames) {
        return playerNames.stream().anyMatch(Dealer::isDealerName);
    }

    private static boolean isDuplicated(List<Name> playerNames) {
        long playerNameCount = playerNames.stream()
            .distinct()
            .count();
        return playerNameCount != playerNames.size();
    }

    private static List<Player> parsePlayersFrom(List<Name> playerNames) {
        return playerNames.stream()
            .map(playerName -> new Player(playerName, new Hand()))
            .toList();
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
