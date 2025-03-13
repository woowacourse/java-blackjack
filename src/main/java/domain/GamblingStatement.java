package domain;

import domain.participant.Participant;
import java.util.HashMap;
import java.util.Map;

public class GamblingStatement {
    private final Map<Participant, Money> gamblingStatement;

    public GamblingStatement(final Map<Participant, Money> gamblingStatement) {
        this.gamblingStatement = new HashMap<>(gamblingStatement);
    }
}
