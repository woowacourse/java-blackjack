package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import java.util.Collections;
import java.util.List;

public class Players {
    private static final int PLAYER_COUNT_LIMIT = 7;

    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayersCount(players);
        this.players = players;
    }

    private void validatePlayersCount(List<Player> players) {
        if (players.size() > PLAYER_COUNT_LIMIT || players.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 2명~7명까지 참여 가능합니다.");
        }
    }

    public void addTwoCards(CardDeck cardDeck) {
        players.forEach((player) -> player.addInitialCards(cardDeck));
    }

    public int calculateTotalPayout(final Dealer dealer) {
        int totalPayout = 0;
        for (Player player : players) {
            totalPayout += player.calculatePayout(dealer);
        }
        return totalPayout;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
