package blackjack.domain.player;

import java.util.List;

import static java.util.stream.Collectors.toList;

public final class Participants {

    public static final String DEALER_NAME = "딜러";

    private final List<Participant> participants;

    private Participants(final List<Participant> participants) {
        validateDuplicate(participants);
        validateName(participants);
        this.participants = participants;
    }

    public static Participants from(final List<Participant> participants) {
        return new Participants(participants);
    }

    private void validateDuplicate(final List<Participant> participants) {
        if (isDuplicate(participants)) {
            throw new IllegalArgumentException("중복되지 않은 이름만 입력해주세요");
        }
    }

    private boolean isDuplicate(final List<Participant> participants) {
        return countDistinctParticipants(participants) != participants.size();
    }

    private long countDistinctParticipants(final List<Participant> participants) {
        return participants.stream()
                .map(Player::getName)
                .distinct()
                .count();
    }

    private void validateName(final List<Participant> participants) {
        if (isParticipantsNameDealer(participants)) {
            throw new IllegalArgumentException("플레이어의 이름은 딜러가 될 수 없습니다.");
        }
    }

    private boolean isParticipantsNameDealer(final List<Participant> participants) {
        return participants.stream()
                .map(Player::getName)
                .anyMatch(name -> name.equals(DEALER_NAME));
    }

    public List<Participant> getParticipants() {
        return List.copyOf(participants);
    }

    public List<String> getNames() {
        return participants.stream()
                .map(Participant::getName)
                .collect(toList());
    }
}
