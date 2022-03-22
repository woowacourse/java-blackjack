package domain.service;

import domain.player.Player;

public interface HitService {
    boolean isHit(String name);

    void showGamblerHitStatus(Player player);

    void showDealerHitStatus(Player player);
}
