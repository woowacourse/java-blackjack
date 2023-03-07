package blackjack.domain.gameplayer;

import blackjack.domain.card.Card;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {
    private final static int MAX_PLAYER_SIZE = 6;
    private final static String INVALID_PLAYER_SIZE_MESSAGE = "게임을 진행하는 플레이어의 수는 1명에서 6명 사이여야 합니다.";
    private final static String INVALID_DUPLICATED_NAME_MESSAGE = "게임을 진행하는 플레이어의 이름은 중복이 없어야합니다.";

    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayersCount(players);
        validateDuplicatedPlayerNames(players);
        this.players = players;
    }

    private void validatePlayersCount(List<Player> players) {
        if (players.isEmpty() || players.size() > MAX_PLAYER_SIZE) {
            throw new IllegalArgumentException(INVALID_PLAYER_SIZE_MESSAGE);
        }
    }

    private void validateDuplicatedPlayerNames(List<Player> players) {
        if (hasDuplicatedNames(players)) {
            throw new IllegalArgumentException(INVALID_DUPLICATED_NAME_MESSAGE);
        }
    }

    private boolean hasDuplicatedNames(List<Player> players) {
        return getDistinctNameCount(players) != players.size();
    }

    private long getDistinctNameCount(List<Player> players) {
        return players.stream()
                .map(player -> player.showName())
                .distinct()
                .count();
    }

    public void addCardToPlayer(Player player, Card card) {
        players.stream()
                .filter(player::equals)
                .forEach(x -> x.addCard(card));
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Player::showName)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
