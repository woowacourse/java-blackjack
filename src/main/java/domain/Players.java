package domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        validate(players);
        this.players = players;
    }

    public static Players from(final List<String> inputtedNames) {
        return new Players(createPlayers(inputtedNames));
    }

    private static List<Player> createPlayers(final List<String> inputtedNames) {
        return inputtedNames.stream()
                .map(inputtedName -> new Player(new Name(inputtedName)))
                .collect(Collectors.toList());
    }

    private void validate(final List<Player> players) {
        validatePlayerNumbers(players);
        validateDuplicate(players);
    }

    private void validatePlayerNumbers(final List<Player> players) {
        if (isInvalidPlayersNumber(players)) {
            throw new IllegalArgumentException("플레이어의 수는 8명을 초과할 수 없습니다.");
        }
    }

    private boolean isInvalidPlayersNumber(final List<Player> players) {
        return players.size() > 8;
    }

    private void validateDuplicate(final List<Player> players) {
        if (hasDuplicatePlayers(players)) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다");
        }
    }

    private boolean hasDuplicatePlayers(final List<Player> players) {
        return Set.copyOf(players).size() != players.size();
    }

    public void dealCards(final Dealer dealer) {
        for (final Player player : players) {
            final List<Card> cards = dealer.drawCards(2);
            player.dealCards(cards);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int size() {
        return players.size();
    }
}
