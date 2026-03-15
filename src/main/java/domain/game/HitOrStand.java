package domain.game;

import exception.BlackjackException;
import java.util.Arrays;

public enum HitOrStand {
    HIT("y"),
    STAND("n");

    public static final String INVALID_YES_NO_INPUT =
            String.format("%s 또는 %s을 입력해야 합니다.", HitOrStand.HIT.getHitOrStand(), HitOrStand.STAND.getHitOrStand());

    private final String hitOrStand;

    HitOrStand(String hitOrStand) {
        this.hitOrStand = hitOrStand;
    }

    public static HitOrStand from(String hitOrStand) {
        return Arrays.stream(values())
                .filter(hs -> hs.getHitOrStand().equals(hitOrStand.strip()))
                .findFirst()
                .orElseThrow(() -> new BlackjackException(INVALID_YES_NO_INPUT));
    }

    public String getHitOrStand() {
        return hitOrStand;
    }

    public boolean isHit() {
        return this.equals(HitOrStand.HIT);
    }

    public boolean isStand() {
        return this.equals(HitOrStand.STAND);
    }
}
