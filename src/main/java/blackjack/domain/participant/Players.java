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

    public Payout calculateTotalPayout(final Dealer dealer) {
        Payout totalPayout = Payout.zero();
        for (Player player : players) {
            Payout playerPayout = player.calculatePayout(dealer);
            totalPayout = totalPayout.add(playerPayout);
        }
        return totalPayout;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
