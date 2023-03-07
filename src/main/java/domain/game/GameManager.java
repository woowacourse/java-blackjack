package domain.game;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;

import java.util.ArrayList;
import java.util.List;

public final class GameManager {

    private static final int DEALER_INDEX = 0;

    private final Deck deck;
    private final List<Participant> participants;

    private GameManager(final Deck deck, final List<Participant> participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public static GameManager create(final Dealer dealer, final List<Player> players, final Deck deck) {
        List<Participant> participants = makeParticipants(dealer, players);
        return new GameManager(deck, participants);
    }

    public void handFirstCards() {
        participants
                .forEach(participant -> participant.addCard(deck.draw(), deck.draw()));
    }

    public void handCard(final Participant participant) {
        participant.addCard(deck.draw());
    }

    private static List<Participant> makeParticipants(final Dealer dealer, final List<Player> players) {
        final List<Participant> participants = new ArrayList<>(players);
        participants.add(DEALER_INDEX, dealer);
        return participants;
    }

    public List<Participant> getParticipants() {
        return List.copyOf(participants);
    }
}
