package domain.participant;

import domain.card.Deck;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO: 2023/03/13 list, dealer분리 
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

    public boolean shouldDealerHit() {
        final Dealer dealer = (Dealer) findDealer();
        return dealer.shouldHit();
    }

    public Map<Participant, Integer> makePlayerFinalHandValue() {
        final Map<Participant, Integer> participantsHandValue = new LinkedHashMap<>();
        for (Participant participant : findPlayers()) {
            participantsHandValue.put(participant, participant.getHandValue());
        }
        return participantsHandValue;
    }

    public List<String> getPlayersName() {
        return findPlayers().stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }
}
