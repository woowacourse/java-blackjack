package domain;

import domain.vo.NameAndCardInfos;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

class Players {

    private final Deque<Player> players;

    Players() {
        this.players = new ArrayDeque<>();
    }

    void add(String name) {
        players.add(Player.of(name));
    }

    List<String> names() {
        return players.stream()
                .map(Participant::name)
                .toList();
    }

    void drawInitialCards(DrawStrategy drawStrategy) {
        players.forEach(player -> drawInitialCards(player, drawStrategy));
    }

    private void drawInitialCards(Player player, DrawStrategy drawStrategy) {
        player.draw(drawStrategy);
        player.draw(drawStrategy);
    }

    NameAndCardInfos currentPlayerCardInfos() {
        return currentPlayer().infos();
    }

    private Player currentPlayer() {
        return players.peek();
    }

    public List<NameAndCardInfos> allPlayerCardInfos() {
        return players.stream()
                .map(Player::infos)
                .toList();
    }
}
