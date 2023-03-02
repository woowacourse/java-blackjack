package domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public final class Participants {

    private final List<Participant> participants;

    private Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(List<String> playerNames) {
        validateDuplicate(playerNames);
        List<Participant> participants = playerNames.stream().map(Participant::from).collect(Collectors.toList());

        return new Participants(participants);
    }

    private static void validateDuplicate(List<String> playerNames) {
        if (isDuplicate(playerNames)) {
            throw new IllegalArgumentException("중복되지 않은 이름만 입력해주세요");
        }

    }

    private static boolean isDuplicate(final List<String> playerNames) {
        final int uniqueNameCount = new HashSet<>(playerNames).size();
        return uniqueNameCount < playerNames.size();
    }

    public void drawCard(final Deck deck) {
        participants.forEach(participant -> {
            participant.takeCard(deck.drawCard());
            participant.takeCard(deck.drawCard());
        });
    }

    public List<Participant> getParticipants() {
        return List.copyOf(participants);
    }
}
