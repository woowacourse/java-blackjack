package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import java.util.List;

public class Participants {

    private final Participant dealer;
    private final List<Participant> players;

    private Participants(Participant dealer,
        List<Participant> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants from(List<Participant> players) {
        return new Participants(new Dealer(), players);
    }

    public void drawBaseCards(Deck deck) {
        for (Participant player : players) {
            drawBaseCardsByParticipant(deck, player);
        }
        drawBaseCardsByParticipant(deck, dealer);
    }

    private void drawBaseCardsByParticipant(Deck deck, Participant participant) {
        if (participant.isReady()) {
            participant.hit(deck.draw());
            participant.hit(deck.draw());
        }
    }

    public Participant getDealer() {
        return dealer;
    }

    public List<Participant> getPlayers() {
        return players;
    }
}
