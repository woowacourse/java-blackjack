package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerBettingTest {
    @Test
    @DisplayName("게임결과가 승이라면 베팅한 금액만큼의 수익을 얻는다.")
    void 게임결과가_승이라면_베팅한_금액만큼의_수익을_얻는다(){
        Player player = new Player("a");
        GameResult gameResult = GameResult.WIN;
        Map<Player, Integer> map = new HashMap<>();
        map.put(player, 10000);
        PlayerBetting playerBetting = new PlayerBetting(map);
        playerBetting.calculateBettingResult(player, gameResult);
        int expect = 20000;
        int result = playerBetting.getBettingNet(player);
        assertEquals(expect, result);
    }

}