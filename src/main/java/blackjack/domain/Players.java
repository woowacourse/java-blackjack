package blackjack.domain;

import java.util.List;

public class Players {
    private final List<Player> playerGroup;

    public Players(List<Player> playerGroup) {
        validate(playerGroup);
        this.playerGroup = playerGroup;
    }

    private static void validate(List<Player> players) {
        if (duplicatedNameExist(players)) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다.");
        }
    }

    private static boolean duplicatedNameExist(List<Player> players) {
        int distinctCount = (int) players.stream()
                .map(Player::getName)
                .distinct()
                .count();

        return distinctCount != players.size();
    }

    public static Players from(List<String> names) {
        List<Player> players = names.stream()
                .map(Player::fromName)
                .toList();

        return new Players(players);
    }

    public void initialDraw(Deck deck, int amount) {
        for (int i = 0; i < amount; i++) {
            drawEachPlayer(deck);
        }
    }

    private void drawEachPlayer(Deck deck) {
        playerGroup.forEach(player -> player.draw(deck));
    }

    public List<Player> getPlayers() {
        return playerGroup;
    }

    public List<String> getPlayerNames() {
        return playerGroup.stream()
                .map(Player::getName)
                .toList();
    }
}
