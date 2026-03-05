package domain;

import java.util.List;

public class Participants {
    private static final Integer MAXIMUM_NUMBER_OF_PARTICIPANTS = 16;

    private List<User> participants;
    private Dealer dealer;

    public Participants(List<String> parsedParticipantsName) {
        validateParticipantsNumbers(parsedParticipantsName);
    }

    static void validateParticipantsNumbers(List<String> parsedParticipantsName) {
        if (parsedParticipantsName.size() > MAXIMUM_NUMBER_OF_PARTICIPANTS) {
            throw new IllegalArgumentException("[ERROR] 최대 참가 인원은 16명 이하여야 합니다.");
        }
    }
}


