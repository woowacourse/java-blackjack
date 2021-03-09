package blackjack.domain;

import blackjack.domain.participants.ParticipantState;
import java.util.Arrays;

public enum Response {
    POSITIVE("y", ParticipantState.HIT),
    NEGATIVE("n", ParticipantState.STAY);

    private final String input;
    private final ParticipantState participantState;

    Response(String input, ParticipantState participantState) {
        this.input = input;
        this.participantState = participantState;
    }

    public static Response getResponse(String input) {
        return Arrays.stream(Response.values())
            .filter(response -> response.input.equalsIgnoreCase(input))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("불가능한 입력 입니다."));
    }

    public ParticipantState getParticipantState() {
        return participantState;
    }
}
