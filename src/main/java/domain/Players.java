package domain;

import java.util.List;
import java.util.Set;

public class Players {
    private final List<Player> players;

    public Players(List<String> userName) {
        this.players = userName.stream()
                .map(Player::new)
                .toList();
    }

    public void giveCard(String username, Card card) {
        Player selectedPlayer = selectPlayer(username);
        selectedPlayer.addCard(card);
    }

    private Player selectPlayer(String username) {
        return players.stream().filter(player -> player.getUsername().equals(username))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 플레이어는 존재하지 않습니다."));
    }

    public Set<Card> getPlayerCard(String username) {
        Player selectedPlayer = selectPlayer(username);

        return selectedPlayer.getCards();
    }
}
