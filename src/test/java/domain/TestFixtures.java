package domain;

import domain.card.CardHand;
import domain.game.GamblingMoney;
import domain.game.Gamblers;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestFixtures {

    private static long id = 0L;

    public static Player playerOfDefaultHand() {
        String name = String.format("플레이어%d", ++id);
        return new Player(name, new CardHand());
    }

    public static Gamblers gamblers(Dealer dealer, List<Player> players) {
        return gamblers(dealer, players, 10000);
    }

    public static Gamblers gamblers(Dealer dealer, List<Player> players, int betAmount) {
        Map<Player, GamblingMoney> map = new LinkedHashMap<>();
        GamblingMoney gamblingMoney = GamblingMoney.bet(betAmount);

        for (Player player : players) {
            map.put(player, gamblingMoney);
        }
        return new Gamblers(dealer, map);
    }
}
