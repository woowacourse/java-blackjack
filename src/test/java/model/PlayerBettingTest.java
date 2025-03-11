package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import model.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerBettingTest {
    @Test
    @DisplayName("게임결과가 승이라면 베팅한 금액만큼의 수익을 얻는 테스트")
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

    @Test
    @DisplayName("게임결과가 패라면 베팅한 금액을 잃는 테스트")
    void 게임결과가_패라면_베팅한_금액을_잃는다(){
        Player player = new Player("a");
        GameResult gameResult = GameResult.LOSE;
        Map<Player, Integer> map = new HashMap<>();
        map.put(player, 10000);
        PlayerBetting playerBetting = new PlayerBetting(map);
        playerBetting.calculateBettingResult(player, gameResult);
        int expect = 0;
        int result = playerBetting.getBettingNet(player);
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("게임결과가 무승부라면 베팅한 손익/손실이 없도록 테스트")
    void 게임결과가_무승부라면_손익_손실이_없다(){
        Player player = new Player("a");
        GameResult gameResult = GameResult.DRAW;
        Map<Player, Integer> map = new HashMap<>();
        map.put(player, 10000);
        PlayerBetting playerBetting = new PlayerBetting(map);
        playerBetting.calculateBettingResult(player, gameResult);
        int expect = 10000;
        int result = playerBetting.getBettingNet(player);
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("게임결과가 블랙잭이라면 베팅한 금액의 1.5배를 받도록 테스트")
    void 게임결과가_블랙잭일경우_수익계산(){
        Player player = new Player("a");
        GameResult gameResult = GameResult.BLACKJACK;
        Map<Player, Integer> map = new HashMap<>();
        map.put(player, 10000);
        PlayerBetting playerBetting = new PlayerBetting(map);
        playerBetting.calculateBettingResult(player, gameResult);
        int expect = 25000;
        int result = playerBetting.getBettingNet(player);
        assertEquals(expect, result);
    }
}