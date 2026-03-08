package blackjack.domain;

import java.util.List;
import java.util.Objects;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        validate(players);
        this.players = List.copyOf(players);
    }

    private void validate(List<Player> players) {
        Objects.requireNonNull(players, "플레이어 목록은 null일 수 없습니다.");
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 1명 이상이어야 합니다.");
        }
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public void receiveCards(Deck deck) {
        for (Player player : players) {
            player.receiveCards(deck.drawSecondTimes());
        }
    }

    public int count() {
        return this.players.size();
    }

    public boolean canHit(int playerIndex) {
        return this.players.get(playerIndex).canHit();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void hitPlayer(int index, TrumpCard card) {
        players.get(index).receiveCard(card);
    }

    public Player playerAt(int index) {
        return players.get(index);
    }
}
