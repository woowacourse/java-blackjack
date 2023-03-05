package blackjack.domain.game;

import blackjack.domain.participant.Participant;
import java.util.function.BiConsumer;

public enum Result {
    WIN("승", (participant, other) -> {
        participant.win();
        other.lose();
    }),
    TIE("무", ((participant, other) -> {
        participant.tie();
        other.tie();
    })),
    LOSE("패", ((participant, other) -> {
        participant.lose();
        other.win();
    }));

    private final String label;
    private final BiConsumer<Participant, Participant> recordResult;

    Result(String label, BiConsumer<Participant, Participant> recordResult) {
        this.label = label;
        this.recordResult = recordResult;
    }

    public String getLabel() {
        return label;
    }

    public void applyTo(Participant participant, Participant other) {
        this.recordResult.accept(participant, other);
    }
}
