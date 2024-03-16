package participant.state;

import participant.Participant;

public interface State {

    boolean isCorespondentState(Participant participant);

    boolean canHit();

    boolean isBust();

    boolean isBlackJack();
}
