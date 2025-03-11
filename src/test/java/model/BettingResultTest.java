package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.participant.Player;
import model.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingResultTest {

    @Test
    @DisplayName("게임결과가 승이라면 베팅한 금액만큼의 수익을 얻는 테스트")
    void 게임결과가_승이라면_베팅한_금액만큼의_수익을_얻는다(){
        Player player = new Player("a");
        Players players = new Players(List.of(player));
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(Map.of(player, GameResult.WIN));
        Map<Player, Integer> map = new HashMap<>();
        map.put(player, 10000);
        BettingResult bettingResult = new BettingResult(map);
        bettingResult.calculatePlayerBettingResult(players, participantWinningResult);
        int expect = 10000;
        int result = bettingResult.getBettingNet(player);
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("게임결과가 패라면 베팅한 금액을 잃는 테스트")
    void 게임결과가_패라면_베팅한_금액을_잃는다(){
        Player player = new Player("a");
        Players players = new Players(List.of(player));
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(Map.of(player, GameResult.LOSE));
        Map<Player, Integer> map = new HashMap<>();
        map.put(player, 10000);
        BettingResult bettingResult = new BettingResult(map);
        bettingResult.calculatePlayerBettingResult(players, participantWinningResult);
        int expect = -10000;
        int result = bettingResult.getBettingNet(player);
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("게임결과가 무승부라면 베팅한 손익/손실이 없도록 테스트")
    void 게임결과가_무승부라면_손익_손실이_없다(){
        Player player = new Player("a");
        Players players = new Players(List.of(player));
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(Map.of(player, GameResult.DRAW));
        Map<Player, Integer> map = new HashMap<>();
        map.put(player, 10000);
        BettingResult bettingResult = new BettingResult(map);
        bettingResult.calculatePlayerBettingResult(players, participantWinningResult);
        int expect = 0;
        int result = bettingResult.getBettingNet(player);
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("게임결과가 블랙잭이라면 베팅한 금액의 1.5배를 받도록 테스트")
    void 게임결과가_블랙잭일경우_수익계산(){
        Player player = new Player("a");
        Players players = new Players(List.of(player));
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(Map.of(player, GameResult.BLACKJACK));
        Map<Player, Integer> map = new HashMap<>();
        map.put(player, 10000);
        BettingResult bettingResult = new BettingResult(map);
        bettingResult.calculatePlayerBettingResult(players, participantWinningResult);
        int expect = 15000;
        int result = bettingResult.getBettingNet(player);
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("딜러의 최종 수익 계산 테스트")
    void 딜러의_최종_수익_계산_테스트(){
        BettingResult bettingResult = new BettingResult(Map.of(
                new Player("a"), 10000,
                new Player("b"), 20000)
        );
        int expect = -30000;
        int result = bettingResult.calculateDealerFinalResult();
        assertEquals(expect, result);
    }
}