package blackjack.domain.participants;

import blackjack.domain.card.Deck;
import blackjack.exceptions.InvalidParticipantsException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    public static final int FIRST_CARDS_COUNT = 2;

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void initialDraw(Deck deck) {
        for (int i = 0; i < FIRST_CARDS_COUNT; i++) {
            dealer.draw(deck.pop());
            players.forEach(player -> player.draw(deck.pop()));
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();

        participants.add(dealer);
        participants.addAll(players);

        return participants;
    }
}
