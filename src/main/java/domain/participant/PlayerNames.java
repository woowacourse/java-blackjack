package domain.participant;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerNames {
    private static final int MAX_SIZE = 8;
    private static final String INVALID_PLAYER_SIZE = "참여자 수는 1명 이상 8명 이하여야 합니다.";
    private static final String PLAYER_NAME_DUPLICATION = "중복된 이름을 허용할 수 없습니다.";

    private final List<ParticipantName> names;

    private PlayerNames(List<ParticipantName> names) {
        validate(names);
        this.names = names;
    }

    public static PlayerNames from(List<String> names) {
        List<ParticipantName> participantNames = names.stream()
                .map(ParticipantName::new)
                .collect(Collectors.toList());

        return new PlayerNames(participantNames);
    }

    private void validate(List<ParticipantName> names) {
        validateSize(names);
        validateDuplication(names);
    }

    private void validateSize(List<ParticipantName> names) {
        if(names.isEmpty() || names.size() > MAX_SIZE){
            throw new IllegalArgumentException(INVALID_PLAYER_SIZE);
        }
    }

    private void validateDuplication(List<ParticipantName> names) {
        long distinctionSize = names.stream()
                .map(ParticipantName::getName)
                .distinct()
                .count();

        if (names.size() != distinctionSize) {
            throw new IllegalArgumentException(PLAYER_NAME_DUPLICATION);
        }
    }

    public List<ParticipantName> getNames() {
        return names;
    }
}
