package domain.player;

public class States {

    private HitState state;

    private States(final HitState state) {
        this.state = state;
    }

    public static States init() {
        return new States(HitState.INIT);
    }

    public void setState(final HitState state) {
        this.state = state;
    }

    public boolean isHit() {
        return state.isHit();
    }

    public boolean isStay() {
        return state.isStay();
    }
}
