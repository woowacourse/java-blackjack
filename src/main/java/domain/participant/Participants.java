package domain.participant;

import error.exception.InvalidParticipantSizeException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participants {

    private static final int PLAYER_MAX_SIZE = 8;
    private static final int DEALER = 1;

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validateParticipantSize(participants);
        this.participants = participants;
    }

    private void validateParticipantSize(List<Participant> participants) {
        if (participants.size() > PLAYER_MAX_SIZE + DEALER) {
            throw new InvalidParticipantSizeException(PLAYER_MAX_SIZE + DEALER);
        }
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public ParticipantsResult calculate() {
        Participant dealer = findDealer();
        Map<Player, GameResult> playersResult = new HashMap<>();
        Map<GameResult, Integer> dealerResult = new HashMap<>();
        for (Participant participant : participants) {
            if (participant.isPlayer()) {
                GameResult gameResult = GameResult.calculateResultOfPlayer(dealer, participant);
                dealerResult.put(gameResult.convertOfDealer(),
                    dealerResult.getOrDefault(gameResult.convertOfDealer(), 0) + 1);
                playersResult.put((Player) participant, gameResult);
            }
        }
        return new ParticipantsResult(playersResult, dealerResult);
    }

    private Participant findDealer() {
        return participants.stream()
            .filter(participant -> !participant.isPlayer())
            .findFirst()
            .orElse(null);
    }
}
