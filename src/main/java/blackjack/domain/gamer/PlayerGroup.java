package blackjack.domain.gamer;

import blackjack.domain.card.CardPack;
import java.util.ArrayList;
import java.util.List;

public class PlayerGroup {
    private static final String NAME_DUPLICATION_ERROR_MESSAGE = "플레이어 이름은 중복될 수 없습니다.";
    private static final String PLAYER_GROUP_SIZE_ERROR_MESSAGE = "플레이어는 최소 1명 이상 최대 8명 이하로 입력해야 합니다.";
    private static final String PLAYER_NOT_EXISTS_ERROR_MESSAGE = "플레이어를 찾을 수 없습니다.";
    private static final int MINIMUM_PLAYER_GROUP_SIZE = 1;
    private static final int MAXIMUM_PLAYER_GROUP_SIZE = 8;

    private final List<Player> players;

    public PlayerGroup(List<Player> players) {
        this.players = players;
        validatePlayers(players);
    }

    private void validatePlayers(List<Player> players) {
        validateSize(players.size());
        validateDuplication(players);
    }

    private void validateSize(int size) {
        if (isProperSize(size)) {
            throw new IllegalArgumentException(PLAYER_GROUP_SIZE_ERROR_MESSAGE);
        }
    }

    private boolean isProperSize(int size) {
        return size < MINIMUM_PLAYER_GROUP_SIZE || size > MAXIMUM_PLAYER_GROUP_SIZE;
    }

    private void validateDuplication(List<Player> players) {
        if (isDuplicated(players)) {
            throw new IllegalArgumentException(NAME_DUPLICATION_ERROR_MESSAGE);
        }
    }

    private boolean isDuplicated(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .distinct()
                .count() != players.size();
    }

    public void addCardToAllPlayers(CardPack cardPack) {
        for (Player player : players) {
            player.addCard(cardPack.pickOne());
        }
    }

    public Player findPlayerByName(String name) {
        return players.stream()
                .filter(it -> it.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_NOT_EXISTS_ERROR_MESSAGE));
    }

    public List<Player> getPlayers() {
        List<Player> copiedPlayers = new ArrayList<>();
        for (Player player : players) {
            Player copiedPlayer = new Player(player.getName(), player.getCards(), player.getBettingMoney());
            copiedPlayers.add(copiedPlayer);
        }
        return copiedPlayers;
    }
}
