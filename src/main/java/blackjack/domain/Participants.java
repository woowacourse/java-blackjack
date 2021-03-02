package blackjack.domain;

import java.util.List;

public class Participants {

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validateOverlappedNames(participants);
        this.participants = participants;
    }

    public void validateOverlappedNames(List<Participant> participants) {
        long participantsCount = participants.size();
        long distinctParticipantsCount = participants.stream()
                                                     .map(participant -> participant.getName())
                                                     .distinct()
                                                     .count();
        if (participantsCount != distinctParticipantsCount) {
            throw new IllegalArgumentException("참가자들의 이름은 중복이 없어야 합니다.");
        }
    }
}
