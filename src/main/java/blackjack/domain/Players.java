package blackjack.domain;

import static java.util.stream.Collectors.toMap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        validateDuplicate(players);
        this.players = players;
    }

    public static Players from(final List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::from)
                .toList();

        return new Players(players);
    }

    private void validateDuplicate(final List<Player> players) {
        if (Set.copyOf(players).size() != players.size()) {
            throw new IllegalArgumentException("중복된 이름의 참여자는 참여할 수 없습니다.");
        }
    }

    public Map<PlayerName, WinStatus> determineWinStatus(final Score dealerScore) {
        Map<PlayerName, WinStatus> playersWinStatus = new LinkedHashMap<>();

        for (Player player : players) {
            playersWinStatus.put(player.getName(), WinStatus.of(dealerScore, player.calculate()));
        }
        return playersWinStatus;
    }

    public void divideCard(final List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            // TODO: 과연 이해가 되는가?
            Player player = players.get(i / 2);
            player.addCard(cards.get(i));
        }
    }

    public int count() {
        return players.size();
    }

    public Map<PlayerName, Hands> getPlayerHands() {
        return players.stream()
                .collect(toMap(Player::getName,
                        Player::getHands,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public Map<PlayerName, Score> getPlayerScores() {
        return players.stream()
                .collect(toMap(Player::getName,
                        Player::calculate,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }
}
