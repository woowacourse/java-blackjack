package blackjack.view;

public enum ParticipantAction {

    HIT,
    STAND,
    ;

    public boolean isHit() {
        return this == HIT;
    }
}
