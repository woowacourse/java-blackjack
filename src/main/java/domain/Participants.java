package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private static final int DEALER_POSITION = 0;
    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants create(List<String> playerNames) {
        validateNotEmptyPlayers(playerNames);
        List<Participant> participants = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        participants.add(DEALER_POSITION, new Dealer());
        return new Participants(participants);
    }

    private static void validateNotEmptyPlayers(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 1명 이상이어야 합니다.");
        }
    }

    public void readyForGame(Deck deck) {
        participants.forEach(participant -> {
            participant.receiveCard(deck.draw());
            participant.receiveCard(deck.draw());
        });
    }

    public boolean hasDrawablePlayer() {
        int firstPlayerPosition = DEALER_POSITION + 1;
        int numberOfParticipants = participants.size();

        return participants.subList(firstPlayerPosition, numberOfParticipants)
                .stream()
                .anyMatch(Participant::isDrawable);
    }
}
