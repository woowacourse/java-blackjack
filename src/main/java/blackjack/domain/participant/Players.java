package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Players {

    private static final int MAX_PLAYERS_SIZE = 4;

    private final List<Player> players;

    public Players(List<Player> players) {
        validateSize(players);
        validateDistinct(players);
        this.players = players;
    }

    private void validateSize(List<Player> players) {
        Objects.requireNonNull(players);
        if (players.isEmpty()) {
            throw new IllegalArgumentException("최소 한 명의 플레이어가 있어야 합니다.");
        }
        if (players.size() > MAX_PLAYERS_SIZE) {
            throw new IllegalArgumentException("최대 4명의 플레이어만 참여 가능합니다.");
        }
    }

    private void validateDistinct(List<Player> players) {
        if (isDuplicated(players)) {
            throw new IllegalArgumentException("중복된 이름을 사용할 수 없습니다.");
        }
    }

    private boolean isDuplicated(List<Player> players) {
        return IntStream.range(0, players.size())
                .anyMatch(index -> isDuplicated(players, index));
    }

    private boolean isDuplicated(List<Player> players, int index) {
        return IntStream.range(0, players.size())
                .filter(currentIndex -> currentIndex != index)
                .anyMatch(currentIndex -> players.get(currentIndex).isEqualName(players.get(index)));
    }

    public void drawStartCards(Deck deck) {
        for (Player player : players) {
            player.drawStartCards(deck);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
