package model.player;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.card.Card;

public class Players {

    private static final String INVALID_PLAYERS_SIZE = "플레이어 수는 1명 이상이어야 합니다.";
    private static final String INVALID_PLAYER_NAMES_UNIQUE = "플레이어 이름은 중복될 수 없습니다.";
    private static final String NOT_EXIST_PLAYER = "존재하는 플레이어가 없습니다.";
    private static final int CARDS_PER_PLAYER = 2;

    private final List<Player> group;

    private Players(List<Player> group) {
        validate(group);
        this.group = group;
    }

    private void validate(List<Player> group) {
        validateEmptyGroup(group);
        validatePlayerNamesUnique(group);
    }

    private void validateEmptyGroup(List<Player> group) {
        if (group.isEmpty()) {
            throw new IllegalArgumentException(INVALID_PLAYERS_SIZE);
        }
    }

    private void validatePlayerNamesUnique(List<Player> group) {
        Set<Player> uniquePlayerGroup = new HashSet<>(group);
        if (uniquePlayerGroup.size() < group.size()) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAMES_UNIQUE);
        }
    }

    public static Players from(List<String> playerNames) {
        return playerNames.stream()
            .map(Player::new)
            .collect(collectingAndThen(toList(), Players::new));
    }

    public Players hitCards(List<Card> cards) {
        int index = 0;
        List<Player> updatedPlayers = new ArrayList<>();
        for (Player player : group) {
            List<Card> cardsToAssign = cards.subList(index, index + CARDS_PER_PLAYER);
            Player updatedPlayer = player.hitCards(cardsToAssign);
            updatedPlayers.add(updatedPlayer);
            index += CARDS_PER_PLAYER;
        }
        return new Players(updatedPlayers);
    }

    public Players hit(Player player, Card card) {
        List<Player> updatedGroup = new ArrayList<>(group);
        Player updatedPlayer = player.hitCard(card);

        int index = updatedGroup.indexOf(player);
        updatedGroup.set(index, updatedPlayer);

        return new Players(updatedGroup);
    }

    public Player findPlayer(Player player) {
        return group.stream()
            .filter(groupPlayer -> groupPlayer.equals(player))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_PLAYER));
    }

    public List<String> names() {
        return group.stream()
            .map(Player::getName)
            .toList();
    }

    public int count() {
        return group.size();
    }

    public List<Player> getGroup() {
        return group;
    }
}
