package blackjack.domain.user;

import blackjack.domain.card.Deck;
import blackjack.domain.user.exception.PlayersException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Players {
    private static final String SPLITTING_DELIMITER = ",";

    private final List<Player> players;

    private Players(List<Player> players) {
        validateDuplicatedName(players);
        validateNames(players);
        this.players = players;
    }

    public static Players of(String playerNames) {
        List<Player> players = Arrays.stream(playerNames.split(SPLITTING_DELIMITER))
                .map(String::trim)
                .map(Player::of)
                .collect(collectingAndThen(toList(),
                        Collections::unmodifiableList));

        return new Players(players);
    }

    private void validateDuplicatedName(List<Player> Players) {
        int distinctCount = (int) Players.stream()
                .map(Player::getName)
                .distinct()
                .count();

        if (Players.size() != distinctCount) {
            throw new PlayersException("중복된 이름이 있으면 안 됩니다.");
        }
    }

    private void validateNames(List<Player> Players) {
        boolean hasDealerName = Players.stream()
                .anyMatch(player -> player.is(Dealer.NAME));

        if (hasDealerName) {
            throw new PlayersException("플레이어의 이름은 딜러일 수 없습니다.");
        }
    }

    public void receiveTwoCards(Deck deck) {
        for (Player player : players) {
            player.receiveInitialCards(deck);
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
