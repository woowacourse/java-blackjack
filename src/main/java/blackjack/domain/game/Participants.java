package blackjack.domain.game;

import java.util.List;

public class Participants {

    private static final int PARTICIPANTS_VALID_SIZE = 7;

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validate(participants);
        this.participants = participants;
    }

    private void validate(List<Participant> participants) {
        if (participants.size() > PARTICIPANTS_VALID_SIZE) {
            throw new IllegalArgumentException("7명을 초과할 수 없습니다.");
        }
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public List<String> getNamesOfParticipants() {
        return participants.stream()
                .filter(Participant::doesHaveName)
                .map(participant -> (Player) participant)
                .map(Player::getName)
                .toList();
    }

    public Participant findDefender() {
        return participants.stream()
                .filter(participant -> !participant.isChallenger())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("게임에 대적할 사람이 존재하지 않습니다."));
    }
}
