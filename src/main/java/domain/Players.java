package domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Players {
    public static final int MAX_PLAYER_QUANTITY = 5;
    private final List<Player> players;

    public Players(List<PlayerName> playerNames, List<BettingMoney> bettingMonies) {
        validatePlayers(playerNames);
        this.players = IntStream.range(0, playerNames.size())
                .mapToObj(i -> new Player(playerNames.get(i), bettingMonies.get(i)))
                .toList();
    }

    private void validatePlayers(List<PlayerName> playerNames) {
        validateDuplicate(playerNames);
        validatePlayerQuantity(playerNames);
    }

    private void validateDuplicate(List<PlayerName> playerNames) {
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복일 수 없습니다.");
        }
    }

    private void validatePlayerQuantity(List<PlayerName> playerNames) {
        if (playerNames.size() > MAX_PLAYER_QUANTITY) {
            throw new IllegalArgumentException("플레이어는 최대 5명까지 참가 가능합니다.");
        }
    }

    public void giveCardsToPlayer(PlayerName playerName, Cards cards) {
        Player selectedPlayer = selectPlayer(playerName);
        selectedPlayer.receiveCards(cards);
    }

    private Player selectPlayer(PlayerName playerName) {
        return players.stream()
                .filter(player -> player.isSameName(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("플레이어가 존재하지 않습니다."));
    }

    public boolean isDrawable(PlayerName playerName) {
        Player player = selectPlayer(playerName);
        return player.isDrawable();
    }

    public Cards getPlayerCard(PlayerName playerName) {
        Player selectedPlayer = selectPlayer(playerName);
        return selectedPlayer.getCards();
    }

    public List<Player> getPlayers() {
        return players.stream()
                .map(Player::copyOf)
                .toList();
    }
}
