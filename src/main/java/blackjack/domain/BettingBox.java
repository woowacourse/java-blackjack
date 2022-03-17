package blackjack.domain;

import blackjack.domain.player.Player;
import java.util.HashMap;
import java.util.Map;

public class BettingBox {
    private final Map<Player, BettingMoney> bettingBox;

    public BettingBox() {
        this.bettingBox = new HashMap<>();
    }

    public void betGuest(Player guest, BettingMoney bettingMoney) {
        bettingBox.put(guest, bettingMoney);
    }

    public int getPrizeMoney(Player guest, WinDrawLose winDrawLose, boolean isBlackjack) {
        BettingMoney bettingMoney = bettingBox.get(guest);
        if (winDrawLose == WinDrawLose.DRAW) {
            return 0;
        }
        if (winDrawLose == WinDrawLose.WIN) {
            return getWinningGuestPrizeMoney(bettingMoney, isBlackjack);
        }
        return bettingMoney.getAmount() * -1;
    }

    private int getWinningGuestPrizeMoney(BettingMoney bettingMoney, boolean isBlackjack) {
        if (isBlackjack) {
            return (int)(bettingMoney.getAmount() * 1.5);
        }
        return bettingMoney.getAmount();
    }
}
