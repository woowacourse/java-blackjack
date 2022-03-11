package blackjack.domain.participant;

public interface CardDrawCallback {

    boolean isContinuable(final String participantName);

    void onUpdate(final Participant participant);
}
