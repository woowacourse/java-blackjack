package blackjack.domain.participant;

public class HitDecision {

    private boolean wantsHit;

    public HitDecision() {
        this.wantsHit = true;
    }

    public void stay() {
        wantsHit = false;
    }

    public boolean wantsHit() {
        return wantsHit;
    }
}
