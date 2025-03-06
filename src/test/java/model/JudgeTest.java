package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JudgeTest {

    private static String name = "pobi";

    @Test
    @DisplayName("플레이어가 승리할 경우 승리를 반환한다")
    void 플레이어가_승리할_경우_테스트(){
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.THREE, CardSuit.DIAMOND));

        Player player = new Player(name);
        player.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));
        Players players = new Players(List.of(player));

        Judge judge = new Judge();
        Map<Player, GameResult> gameResult = judge.decidePlayerWinning(players, dealer);
        assertEquals(gameResult.get(player), GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어가 패배할 경우 패배를 반환한다.")
    void 플레이어가_패배할_경우_테스트(){
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));

        Player player = new Player(name);
        player.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.THREE, CardSuit.DIAMOND));

        Players players = new Players(List.of(player));

        Judge judge = new Judge();
        Map<Player, GameResult> gameResult = judge.decidePlayerWinning(players, dealer);
        assertEquals(gameResult.get(player), GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어가 무승부일 경우 무승부를 반환한다.")
    void 플레이어가_무승부일_경우_테스트(){
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));

        Player player = new Player(name);
        player.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));

        Players players = new Players(List.of(player));

        Judge judge = new Judge();
        Map<Player, GameResult> gameResult = judge.decidePlayerWinning(players, dealer);
        assertEquals(gameResult.get(player), GameResult.DRAW);
    }

    @Test
    @DisplayName("심판이 딜러의 최종 승패를 결정한다.")
    void 심판이_딜러의_최종_승패를_결정한다() {
        GameResult playerResult1 = GameResult.WIN;
        GameResult playerResult2 = GameResult.DRAW;
        Map<Player, GameResult> playerGameResult =
                Map.of(new Player("a"), playerResult1, new Player("b"), playerResult2);
        Judge judge = new Judge();
        //when
        Map<GameResult, Integer> dealerResults = judge.decideDealerWinning(playerGameResult);
        int expect = 1;
        int winExpect = 0;
        int loseCount = dealerResults.getOrDefault(GameResult.LOSE, 0);
        int drawCount = dealerResults.getOrDefault(GameResult.DRAW, 0);
        int winCount = dealerResults.getOrDefault(GameResult.WIN, 0);

        //then
        assertEquals(loseCount, expect);
        assertEquals(drawCount, expect);
        assertEquals(winCount, winExpect);
    }

}