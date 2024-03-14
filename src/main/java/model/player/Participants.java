package model.player;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Participants {
    public static final int MINIMUM_PARTICIPANT_SIZE = 2;
    public static final int MAXIMUM_PARTICIPANT_SIZE = 8;
    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validateNotDuplicatedParticipant(participants);
        validateParticipantSize(participants);
        this.participants = participants;
    }

    private void validateNotDuplicatedParticipant(List<Participant> participants) {
        Set<User> duplicates = participants.stream()
                .filter(n -> Collections.frequency(participants, n) > 1)
                .collect(Collectors.toSet());

        if (!duplicates.isEmpty()) {
            String duplicatedName = duplicates.stream()
                    .map(User::getName)
                    .collect(Collectors.joining(","));
            throw new IllegalArgumentException("중복된 이름(" + duplicatedName + ")가 있습니다, 참가자들의 이름은 중복되면 안됩니다.");
        }
    }

    private void validateParticipantSize(List<Participant> participants) {
        if (participants.size() < MINIMUM_PARTICIPANT_SIZE || participants.size() > MAXIMUM_PARTICIPANT_SIZE) {
            throw new IllegalArgumentException("참가자의 수는 2~8명이어야 합니다.");
        }
    }

    public boolean isExistParticipant(Participant receiver) {
        return participants.stream()
                .anyMatch(player -> player.equals(receiver));
    }

    public List<String> findParticipantsName() {
        return participants.stream()
                .map(participant -> participant.name).toList();
    }

    public Double findDealerProfit(Dealer dealer) {
        return participants.stream()
                .mapToDouble(participant -> participant.calculateProfit(dealer) * -1.0)
                .sum();
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
