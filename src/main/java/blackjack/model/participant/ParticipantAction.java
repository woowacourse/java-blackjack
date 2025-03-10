package blackjack.model.participant;

public enum ParticipantAction {

    HIT,
    STAND,
    ;

    public boolean isHit() {
        return this == HIT;
    }
}
