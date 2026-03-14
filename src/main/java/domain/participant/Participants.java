package domain.participant;

import domain.Deck;
import java.util.List;

public class Participants {
    private final Dealer dealer;
    private final Players players;

    private Participants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants of(Dealer dealer, Players players) {
        return new Participants(dealer, players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> players() {
        return players.getPlayers();
    }

    public void distributeCardsToAll(Deck deck, int cardCount) {
        dealer.addAll(deck.draw(cardCount));
        players.distributeCardsToAll(deck, cardCount);
    }
}
