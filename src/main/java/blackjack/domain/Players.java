package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Players {
    private final List<Player> values;

    public Players(final List<Player> players) {
        validateDuplicate(players);
        this.values = players;
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

    public Map<Name, WinStatus> determineWinStatus(final Score dealerScore) {
        Map<Name, WinStatus> playersWinStatus = new LinkedHashMap<>();

        for (Player player : values) {
            playersWinStatus.put(player.getName(), WinStatus.of(dealerScore, player.calculate()));
        }
        return playersWinStatus;
    }

    public void divideCard(final List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            // TODO: 과연 이해가 되는가?
            Player player = values.get(i / 2);
            player.addCard(cards.get(i));
        }
    }

    public int count() {
        return values.size();
    }
}
