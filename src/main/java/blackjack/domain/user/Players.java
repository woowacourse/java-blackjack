package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.user.exception.PlayersException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Players {
    private static final String SPLITTING_DELIMITER = ",";

    private final List<User> players;

    private Players(List<User> players) {
        validateDuplicatedName(players);
        validateNames(players);
        this.players = players;
    }

    public static Players of(String playerNames) {
        List<User> players = Arrays.stream(playerNames.split(SPLITTING_DELIMITER))
                .map(String::trim)
                .map(Player::of)
                .collect(collectingAndThen(toList(),
                        Collections::unmodifiableList));

        return new Players(players);
    }

    private void validateDuplicatedName(List<User> Players) {
        int distinctCount = (int) Players.stream()
                .map(User::getName)
                .distinct()
                .count();

        if (Players.size() != distinctCount) {
            throw new PlayersException("중복된 이름이 있으면 안 됩니다.");
        }
    }

    private void validateNames(List<User> Players) {
        boolean hasDealerName = Players.stream()
                .anyMatch(player -> player.is(Dealer.NAME));

        if (hasDealerName) {
            throw new PlayersException("플레이어의 이름은 딜러일 수 없습니다.");
        }
    }

    public void giveCards(int index, Card... cards) {
        for (Card card : cards) {
            players.get(index)
                    .giveCards(card);
        }
    }

    public int memberSize() {
        return players.size();
    }

    public List<User> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
