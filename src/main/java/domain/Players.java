package domain;

import java.util.List;

public class Players {
    private static final int MAX_SIZE = 6;

    private final List<Player> players;

    public Players(List<Player> players) {
        validateSize(players);
        this.players = players;
    }

    private void validateSize(List<Player> players) {
        if (players.size() < 2 || players.size() > MAX_SIZE) {
            throw new IllegalArgumentException("참여자 수는 딜러 포함 최소 2인 이상 최대 6인 이하여야 합니다.");
        }
    }

    public void distributeTwoCards(List<Card> cards) {
        if (cards.size() != 2 * size()) {
            throw new IllegalArgumentException(size() + ": 카드가 부족해서 2장씩 나눠줄 수 없습니다.");
        }

        for (Player player : players) {
            player.receiveCards(List.of(cards.removeLast(), cards.removeLast()));
        }
    }

    public int size() {
        return players.size();
    }
}
