package blackjack.domain.participant;

import blackjack.domain.bet.Bet;
import blackjack.domain.bet.Bets;
import blackjack.domain.card.Deck;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Gamblers {

    private final Players players;
    private final Bets bets;

    public Gamblers(Players players, Bets bets) {
        this.players = players;
        this.bets = bets;
    }

    public void distributeCards(Deck deck) {
        players.distributeCards(deck);
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Set<Entry<Player, Bet>> getBets() {
        return bets.getBets();
    }

    public Player getCurrentPlayer() {
        return players.getDrawablePlayer();
    }

    public long getDealerProfit(Map<Player, Integer> playerProfits) {
        return -playerProfits.values().stream()
                .mapToLong(Integer::longValue)
                .sum();
    }

    public Map<Player, Integer> determinePlayerProfits(Dealer dealer) {
        return bets.determinePlayerProfits(dealer);
    }
}
