package domain.participant;

import java.util.Collections;
import java.util.List;

public class Participants {
    private static final int PLAYER_MAX_SIZE = 8;
    private static final int DEALER = 1;

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validateParticipantSize(participants);
        validateParticipantNameDuplication(participants);
        this.participants = participants;
    }

    private void validateParticipantSize(List<Participant> participants) {
        if (participants.size() > PLAYER_MAX_SIZE + DEALER) {
            throw new IllegalArgumentException("게임의 최대 참여자는 %d명을 넘을 수 없습니다.".formatted(
                    PLAYER_MAX_SIZE));
        }
    }

    private void validateParticipantNameDuplication(List<Participant> participants) {
        long distinctCount = participants.stream()
                .map(Participant::getName)
                .distinct()
                .count();
        if (distinctCount != participants.size()) {
            throw new IllegalArgumentException("참여자의 이름은 중복될 수 없습니다.");
        }
    }

    public Participant findByName(String name) {
        return participants.stream()
                .filter(participant -> participant.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 참가자가 존재하지 않습니다."));
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public List<Participant> getPlayerParticipants() {
        return participants.stream()
                .filter(participant -> participant.getClass() == Player.class)
                .toList();
    }

    public List<String> getPlayerNames() {
        return participants.stream()
                .filter(participant -> participant.getClass() == Player.class)
                .map(Participant::getName)
                .toList();
    }

    public Participant getDealer() {
        return participants.stream()
                .filter(participant -> participant.getClass() == Dealer.class)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("딜러가 존재하지 않습니다."));
    }
}
