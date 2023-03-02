package domain.participant;

import domain.card.Deck;

import java.util.List;

public class Participants {

    private final Participant dealer;
    private final Players players;

    private Participants(Participant dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants from(List<String> playersName) {
        return new Participants(new Dealer(), Players.of(playersName));
    }

    public void initHand(Deck deck) {
        dealer.initHand(deck.pollTwoCards());
        players.initPlayersHand(deck);
    }

    public Participant getDealer() {
        return dealer;
    }

    public List<Participant> getPlayers() {
        return players.toList();
    }
}
