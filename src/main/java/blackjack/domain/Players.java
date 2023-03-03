package blackjack.domain;

import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayersCount(players);
        this.players = players;
    }

    private void validatePlayersCount(List<Player> players) {
        if (players.isEmpty() || players.size() > 6) {
            throw new IllegalArgumentException("게임을 진행하는 플레이어의 수는 1명에서 6명 사이여야 합니다.");
        }
    }

    public boolean getPlayerIsHit(int i) {
        return players.get(i).isHit();
    }

    public void addCardToPlayer(int i, Card card) {
        players.get(i).addCard(card);
    }
}
