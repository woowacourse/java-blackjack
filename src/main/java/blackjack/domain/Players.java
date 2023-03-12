package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        validatePlayersCount(players);
        this.players = players;
    }

    public static Players from(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::from)
                .collect(Collectors.toList());
        return new Players(players);
    }

    private void validatePlayersCount(List<Player> players) {
        if (players.isEmpty() || players.size() > 6) {
            throw new IllegalArgumentException("게임을 진행하는 플레이어의 수는 1명에서 6명 사이여야 합니다.");
        }
    }

    public List<Integer> getPlayersScore() {
        List<Integer> playersScore = new ArrayList<>();
        for (Player player : players) {
            playersScore.add(player.calculateScore());
        }
        return playersScore;
    }

    public void addCardToPlayer(int i, Card card) {
        players.get(i).addCard(card);
    }

    public Player getPlayer(int i) {
        return players.get(i);
    }

    public int getCount() {
        return players.size();
    }
}
