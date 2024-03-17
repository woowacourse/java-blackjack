package blackjack.domain.betting;

import blackjack.domain.gamer.Gamer;

import java.util.HashMap;
import java.util.Map;

public class Bettings {
    private static final int MONEY_MAX = 100000000;
    private static final int MONEY_MIN = 1000;

    private final Map<Gamer, Integer> playersBetting;

    public Bettings() {
        this(new HashMap<>());
    }

    public Bettings(final Map<Gamer, Integer> playersBetting) {
        this.playersBetting = new HashMap<>(playersBetting);
    }

    public void add(final Gamer gamer, final int money) {
        if (money < MONEY_MIN || money > MONEY_MAX) {
            throw new IllegalArgumentException("배팅 금액이 1000원 이상 100000000원 이하여야 합니다.");
        }
        playersBetting.put(gamer, money);
    }
}
