package domain;

public enum Answer {

    HIT,
    STAY;

    public boolean isHit() {
        return HIT.equals(this);
    }
}
