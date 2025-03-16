package model.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.card.CardHand;
import model.card.Deck;

public final class Participants {
    private final Dealer dealer;
    private final Players players;

    private Participants(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants initialize(final Map<Name, Bet> registerInput, final Deck deck) {
        final Dealer dealer = new Dealer(CardHand.drawInitialHand(deck));
        final Players players = new Players(registerInput, deck);
        return new Participants(dealer, players);
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayers());
        return participants;
    }

    public Players getPlayers() {
        return players;
    }
}
