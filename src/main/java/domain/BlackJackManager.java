package domain;

import java.util.List;
import java.util.Objects;

public class BlackJackManager {
    private final List<Player> players;
    private final CardDeck cardDeck;

    public BlackJackManager(List<Player> players, CardDeck cardDeck) {
        this.players = players;
        this.cardDeck = cardDeck;
    }

    public List<Player> getPlayers() {
        return players;
    }

    // 게임이 시작되면 모든 플레이어에게 카드를 2장씩 나눠준다.
    public void start() {
        for (Player player : players) {
            player.addCard(cardDeck.getAndRemoveFrontCard());
            player.addCard(cardDeck.getAndRemoveFrontCard());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BlackJackManager that = (BlackJackManager) o;
        return Objects.equals(players, that.players);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(players);
    }
}
