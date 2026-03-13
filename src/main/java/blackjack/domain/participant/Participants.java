package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import java.util.List;
import java.util.stream.Stream;

public class Participants {

    private final Players players;
    private final Dealer dealer;

    public Participants(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void distributeCards(Deck deck) {
        dealer.drawInitialCards(deck);
        players.distributeCards(deck);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<Participant> getParticipants() {
        return Stream
                .concat(Stream.of(dealer), players.getPlayers().stream())
                .toList();
    }

    public Player getCurrentPlayer() {
        return players.getDrawablePlayer();
    }
}
