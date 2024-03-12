package model.player;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.card.Card;

public class Players {

    private static final String INVALID_PLAYERS_SIZE = "플레이어 수는 1명 이상이어야 합니다.";
    private static final String INVALID_PLAYER_NAMES_UNIQUE = "플레이어 이름은 중복될 수 없습니다.";
    private static final int CARDS_PER_PLAYER = 2;

    private final List<Player> group;

    private Players(List<Player> group) {
        this.group = group;
    }

    public static Players from(List<String> playerNames) {
        validate(playerNames);
        return playerNames.stream()
            .map(Player::new)
            .collect(collectingAndThen(toList(), Players::new));
    }

    private static void validate(List<String> playerNames) {
        validateEmpty(playerNames);
        validatePlayerNamesUnique(playerNames);
    }

    private static void validateEmpty(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException(INVALID_PLAYERS_SIZE);
        }
    }

    private static void validatePlayerNamesUnique(List<String> playerNames) {
        Set<String> uniqueNames = new HashSet<>(playerNames);
        if (uniqueNames.size() < playerNames.size()) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAMES_UNIQUE);
        }
    }

    public void hitCards(List<Card> cards) {
        int index = 0;
        for (Player player : group) {
            List<Card> cardsToAssign = cards.subList(index, index + CARDS_PER_PLAYER);
            player.hitCards(cardsToAssign);
            index += CARDS_PER_PLAYER;
        }
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
