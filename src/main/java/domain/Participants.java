package domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
        int dealerValue = 0;
        for (Participant participant : participants) {
            if (participant instanceof Dealer) {
                dealerValue = participant.getTotalValue();
                break;
            }
        }

        Set<ParticipantResult> tmpResult = new LinkedHashSet<>();
        ParticipantResult dealerResult = new DealerResult();
        tmpResult.add(dealerResult);

        for (Participant participant : participants) {
            if (participant instanceof Player) {
                GameResult gameResult = GameResult.calculateDealerResult(dealerValue,
                    participant.getTotalValue());
                ParticipantResult playerResult = new PlayerResult(participant.getName(),
                    gameResult.reverse());
                dealerResult.add(gameResult);
                tmpResult.add(playerResult);
            }
        }
        return new ParticipantsResult(tmpResult);
    }
}
