package model.participant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Deck.Deck;

public final class Participants {
    private final List<Participant> participants;

    public static Participants from(List<String> playerNames) {
        validatePlayerDuplication(playerNames);

        List<Participant> participants = new ArrayList<>();
        playerNames.forEach(playerName ->
                participants.add(new Player(playerName))
        );
        participants.add(new Dealer());
        return new Participants(participants);
    }

    public Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public void dealInitialCards(final Deck deck) {
        participants.forEach(participant ->
                participant.dealInitialCards(deck)
        );
    }

    private static void validatePlayerDuplication(final List<String> playerNames) {
        Set<String> uniqueNames = new HashSet<>(playerNames);
        if (uniqueNames.size() != playerNames.size()) {
            throw new IllegalArgumentException("플레이어 이름 간 중복은 허용하지 않습니다.");
        }
    }

    public List<Player> getPlayers() {
        return participants.stream()
                .filter(participant -> participant instanceof Player)
                .map(participant -> (Player) participant)
                .toList();
    }

    public Dealer getDealer() {
        return participants.stream()
                .filter(participant -> participant instanceof Dealer)
                .findAny()
                .map(participant -> (Dealer) participant)
                .orElseThrow(() -> new IllegalStateException("딜러가 존재하지 않습니다."));
    }
}
