package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.user.exception.PlayersException;

import java.util.*;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Players {
    private final List<User> players;

    private Players(List<User> players) {
        validateDuplicatedName(players);
        this.players = players;
    }

    public static Players of(String playerNames) {
        List<User> players = Arrays.stream(playerNames.split(","))
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

    public Map<User, Boolean> generateResult(User dealer) {
        Map<User, Boolean> playerResults = new HashMap<>();
        Score dealerScore = dealer.calculateScore();
        for (User player : players) {
            playerResults.put(player, player.isWinner(dealerScore));
        }

        return playerResults;
    }

    public void giveCard(String name, Card card) {
        User selectedPlayer = players.stream()
                .filter(player -> player.is(name))
                .findFirst()
                .orElseThrow(() -> new PlayersException("해당하는 이름을 가진 player가 없습니다."));

        selectedPlayer.append(card);
    }
}
