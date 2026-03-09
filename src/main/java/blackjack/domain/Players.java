package blackjack.domain;

import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(List<String> playersName) {
        validateNumberOfPlayers(playersName);
        this.players = playersName.stream()
                .map(Player::new)
                .toList();
    }

    public static Players from(List<String> playersName) {
        return new Players(playersName);
    }

    public void draw() {
        for (Player player : players) {
            player.draw(Deck.pop());
        }
    }

    public boolean isAllPlayersBurst() {
        int burstUserCount = (int) players.stream()
                .filter(Player::isBurst)
                .count();
        return players.size() == burstUserCount;
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return players;
    }

    private void validateNumberOfPlayers(List<String> playersName) {
        if (playersName.size() > 5) {
            throw new IllegalArgumentException("게임에 참가하는 플레이어는 5인 이하입니다. (딜러 제외)");
        }
    }

}
