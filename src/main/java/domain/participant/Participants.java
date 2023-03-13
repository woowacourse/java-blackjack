package domain.participant;

import domain.card.Deck;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants {
    private final List<Player> players;
    private final Participant dealer;

    private Participants(List<Player> participants) {
        this.players = participants;
        dealer = new Dealer();
    }

    public static Participants from(List<Player> players) {
        return new Participants(players);
    }

    public void deal(Deck deck) {
        dealer.receiveCard(deck.draw());
        for (Participant player : players) {
            player.receiveCard(deck.draw());
        }
    }

    public Participant findDealer() {
        return dealer;
    }

    public List<Participant> findPlayers() {
        return new ArrayList<>(players);
    }

    public boolean shouldDealerHit(Deck deck) {
        if (dealer.shouldHit()) {
            dealer.receiveCard(deck.draw());
            return true;
        }
        return false;
    }

    public Map<Participant, Integer> makePlayerFinalHandValue() {
        final Map<Participant, Integer> participantsHandValue = new LinkedHashMap<>();
        for (Participant participant : players) {
            participantsHandValue.put(participant, participant.getHandValue());
        }
        return participantsHandValue;
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }
}
