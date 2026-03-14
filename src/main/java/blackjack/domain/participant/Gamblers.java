package blackjack.domain.participant;

import blackjack.domain.bet.Bet;
import blackjack.domain.bet.Bets;
import blackjack.domain.card.Deck;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class Participants {

    private final Players players;
    private final Bets bets;

    public Participants(Players players, Bets bets) {
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
}
