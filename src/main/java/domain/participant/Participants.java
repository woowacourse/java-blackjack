package domain.participant;

import domain.DomainException;
import domain.ExceptionCode;
import domain.card.Deck;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO: 2023/03/13 list, dealer분리 
public class Participants {
//    private final List<Participant> players;
//    private final Participant dealer;

    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
//        this.players = participants;
//        dealer = new Dealer();
        this.participants = participants;
    }

    public static Participants from(List<Player> players) {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer());
        participants.addAll(players);
        return new Participants(participants);
    }

    public void deal(Deck deck) {
        for (Participant participant : participants) {
            participant.receiveCard(deck.draw());
        }
    }

    public Participant findDealer() {
        return participants.stream()
                .filter(participant -> participant.getClass().equals(Dealer.class))
                .findFirst()
                .orElseThrow(() -> new DomainException(ExceptionCode.NO_DEALER)
                );
    }

    public List<Participant> findPlayers() {
        List<Participant> players = new ArrayList<>();
        for (Participant participant : participants) {
            addParticipantIfPlayer(players, participant);
        }
        return players;
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

    private void addParticipantIfPlayer(List<Participant> players, Participant participant) {
        if (participant.getClass().equals(Player.class)) {
            players.add(participant);
        }
    }

    public List<String> getPlayersName() {
        return findPlayers().stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }
}
