package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.betting.BettingAmount;
import domain.betting.BettingManager;
import domain.participant.Name;
import domain.participant.Player;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class BettingManagerTest {

    @Test
    void 참가자가_패배하면_배팅금액은_0원이_된다() {
        Player player = new Player(new Name("pobi"));
        Map<Name, BettingAmount> amountMap = new HashMap<>();
        amountMap.put(player.getName(), new BettingAmount(1000));
        BettingManager bettingManager = new BettingManager(amountMap);
        bettingManager.lose(player.getName());
        int expected = 0;
        int actual = bettingManager.getAmount(player.getName()).getMoney();
        assertEquals(expected, actual);
    }

    @Test
    void 참가자가_승리하면_배팅금액을_받는다() {
        Player player = new Player(new Name("pobi"));
        Map<Name, BettingAmount> amountMap = new HashMap<>();
        BettingAmount bettingAmount = new BettingAmount(1000);
        amountMap.put(player.getName(), bettingAmount);
        BettingManager bettingManager = new BettingManager(amountMap);
        bettingManager.win(player.getName());
        int expected = 1000;
        int actual = bettingManager.getAmount(player.getName()).getMoney();
        assertEquals(expected, actual);
    }

    @Test
    void 참가자가_블랙잭이면_배팅금액을_받는다() {
        Player player = new Player(new Name("pobi"));
        Map<Name, BettingAmount> amountMap = new HashMap<>();
        BettingAmount bettingAmount = new BettingAmount(1000);
        amountMap.put(player.getName(), bettingAmount);
        BettingManager bettingManager = new BettingManager(amountMap);
        bettingManager.blackJackWin(player.getName());
        int expected = 1500;
        int actual = bettingManager.getAmount(player.getName()).getMoney();
        assertEquals(expected, actual);
    }
}
