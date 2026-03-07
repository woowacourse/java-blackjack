package constant;

public enum HitOrStand {
    HIT("y"),
    STAND("n");

    private final String hitOrStand;

    HitOrStand(String hitOrStand) {
        this.hitOrStand = hitOrStand;
    }

    public String getHitOrStand() {
        return hitOrStand;
    }
}
