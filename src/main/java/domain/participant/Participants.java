package domain.participant;

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
            throw new IllegalArgumentException("게임의 최대 참여자는 %d명을 넘을 수 없습니다.".formatted(
                PLAYER_MAX_SIZE));
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
                GameResult gameResult = GameResult.calculateDealerResult(dealer, participant);
                dealerResult.put(gameResult, dealerResult.getOrDefault(gameResult, 0) + 1);
                playersResult.put((Player) participant, gameResult.reverse());
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
