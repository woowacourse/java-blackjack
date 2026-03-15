package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.Arrays;
import java.util.List;

public record PlayerGroup(List<Player> players) {
    private static final String DELIMITER = ",";
    private static final int INCLUDE_EMPTY_ELEMENT = -1;

    public static PlayerGroup from(final String rawPlayerNames) {
        List<Player> players = parsePlayersFrom(rawPlayerNames);
        return new PlayerGroup(players);
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
        return playerNames.stream().distinct().count() != playerNames.size();
    }

    private static List<Player> parsePlayersFrom(List<Name> playerNames) {
        return playerNames.stream()
            .map(playerName -> new Player(playerName, new Hand()))
            .toList();
    }

    @Override
    public List<Player> players() {
        return List.copyOf(players);
    }

    public void deal(Deck deck) {
        players.forEach(participant -> participant.hit(deck.draw()));
    }
}
