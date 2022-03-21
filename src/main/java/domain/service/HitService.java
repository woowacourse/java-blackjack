package domain.service;

import domain.player.Player;
import domain.player.Players;

public interface HitService {
    void showInitCards(Players players);

    boolean isHit(String name);

    default void showCardStatus(Player player) {
        if (player.isDealer()) {
            showDealerHitStatus(player);
        }

        showGamblerHitStatus(player);
    }

    void showGamblerHitStatus(Player player);

    void showDealerHitStatus(Player player);
}
