package domain;

import view.ErrorMessage;

import java.util.*;
import java.util.stream.Collectors;

public class Participants {
    private static final int MINIMUM_PLAYER_COUNT = 1;
    private static final int MAXIMUM_PLAYER_COUNT = 7;

    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(List<String> names) {
        validate(names);
        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer());
        names.forEach(name -> participants.add(new Player(name)));
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
            .orElseThrow(() -> new IllegalStateException(ErrorMessage.NO_DEALER.getMessage())
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

    private static void validate(List<String> names) {
        validateNumberOfNames(names);
        validateNoDuplication(names);
    }

    private static void validateNumberOfNames(List<String> names) {
        if (names.size() < MINIMUM_PLAYER_COUNT || names.size() > MAXIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_OF_PLAYER.getMessage());
        }
    }

    private static void validateNoDuplication(List<String> names) {
        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException(ErrorMessage.NAME_IS_DUPLICATED.getMessage());
        }
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
