package blackjack.domain;

import blackjack.domain.participants.ParticipantStatus;
import java.util.Arrays;

public enum Response {
    POSITIVE("y", ParticipantStatus.HIT),
    NEGATIVE("n", ParticipantStatus.STAY);

    private final String input;
    private final ParticipantStatus participantStatus;

    Response(String input, ParticipantStatus participantStatus) {
        this.input = input;
        this.participantStatus = participantStatus;
    }

    public static Response getResponse(String input) {
        return Arrays.stream(Response.values())
            .filter(response -> response.input.equalsIgnoreCase(input))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("불가능한 입력 입니다."));
    }

    public ParticipantStatus getPlayerStatus() {
        return participantStatus;
    }
}
