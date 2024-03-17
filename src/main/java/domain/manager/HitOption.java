package domain.manager;

public enum HitOption {

    HIT,
    NOT_HIT;

    public boolean isHit() {
        return this == HIT;
    }
}
