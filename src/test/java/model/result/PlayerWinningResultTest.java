package model.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import model.deck.Card;
import model.deck.CardRank;
import model.deck.CardSuit;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerWinningResultTest {
    private Dealer dealer;
    private Player player;
    private Players players;

    @BeforeEach
    void setUp() {
        players = Players.from(List.of("moda"));
        dealer = new Dealer();
        player = players.getPlayers().getFirst();
    }

    @Test
    @DisplayName("딜러와 플레이어를 받아 플레이어 기준 승패 결과를 저장한다")
    void 플레이어_기준_승패결과를_저장() {
        //given
        player.receiveCard(new Card(CardRank.ACE, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.NINE, CardSuit.DIAMOND));
        PlayerWinningResult playerWinningResult = new PlayerWinningResult(player, GameResult.LOSE);

        //when, then
        assertThat(playerWinningResult.isLose()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 졌는지 확인한다")
    void 플레이어가_졌는지_확인한다() {
        //given
        PlayerWinningResult playerWinningResult = new PlayerWinningResult(player, GameResult.LOSE);

        //when, then
        assertThat(playerWinningResult.isLose()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 블랙잭 승인지 확인한다")
    void 플레이어가_블랙잭승인지_확인한다() {
        //given
        player.receiveCard(new Card(CardRank.ACE, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.JACK, CardSuit.DIAMOND));
        PlayerWinningResult playerWinningResult = new PlayerWinningResult(player, GameResult.WIN);

        //when, then
        assertThat(playerWinningResult.isBlackjackWin()).isTrue();
    }

    @Test
    @DisplayName("딜러가 버스트되고 플레이어의 경우 버스트가 아닌 경우 플레이어가 승리한다")
    void decideDealerWinning() {
        //given
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.FIVE, CardSuit.DIAMOND));

        //when
        WinningResults winningResults = WinningResults.of(players, dealer);
        GameResult result = winningResults.getResults().get(player);
        GameResult expect = GameResult.WIN;

        //then
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("플레이어가 버스트되고 딜러는 버스트되지 않을 때 플레이어는 패배한다")
    void 플레이어가_버스트되고_딜러는_버스트되지_않을_때() {
        //given
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.CLOVER));
        player.receiveCard(new Card(CardRank.FIVE, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.QUEEN, CardSuit.DIAMOND));

        //when
        WinningResults winningResults = WinningResults.of(players, dealer);
        GameResult result = winningResults.getResults().get(player);
        GameResult expect = GameResult.LOSE;

        //then
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 버스트 되었을 때 플레이어는 패배한다")
    void 플레이와_딜러_모두_버스트_되었을_때_플레이어는_패배한다() {
        //given
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.HEART));
        player.receiveCard(new Card(CardRank.FIVE, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.QUEEN, CardSuit.DIAMOND));

        //when
        WinningResults winningResults = WinningResults.of(players, dealer);
        GameResult result = winningResults.getResults().get(player);
        GameResult expect = GameResult.LOSE;

        //then
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("플레이어가 점수 승리할 경우 승리를 반환한다")
    void 플레이어가_승리할_경우_테스트() {
        //given
        dealer.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.THREE, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));

        //when
        WinningResults winningResults = WinningResults.of(players, dealer);
        Map<Player, GameResult> gameResult = winningResults.getResults();
        GameResult expect = GameResult.WIN;

        //then
        assertEquals(gameResult.get(player), expect);
    }

    @Test
    @DisplayName("플레이어가 점수 패배할 경우 패배를 반환한다.")
    void 플레이어가_패배할_경우_테스트() {
        //given
        dealer.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.THREE, CardSuit.DIAMOND));

        //when
        WinningResults winningResults = WinningResults.of(players, dealer);
        Map<Player, GameResult> gameResult = winningResults.getResults();
        GameResult expect = GameResult.LOSE;

        //then
        assertEquals(gameResult.get(player), expect);
    }

    @Test
    @DisplayName("플레이어가 점수 무승부일 경우 무승부를 반환한다.")
    void 플레이어가_무승부일_경우_테스트() {
        //given
        dealer.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));

        //when
        WinningResults winningResults = WinningResults.of(players, dealer);
        Map<Player, GameResult> gameResult = winningResults.getResults();
        GameResult expect = GameResult.DRAW;

        //then
        assertEquals(gameResult.get(player), expect);
    }

    @Test
    @DisplayName("플레이어가 블랙잭 승리할 경우 승리를 반환한다")
    void 플레이어가_블랙잭_승리할_경우_테스트() {
        //given
        dealer.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.THREE, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.ACE, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.JACK, CardSuit.DIAMOND));

        //when
        WinningResults winningResults = WinningResults.of(players, dealer);
        Map<Player, GameResult> gameResult = winningResults.getResults();
        GameResult expect = GameResult.WIN;

        //then
        assertEquals(gameResult.get(player), expect);
    }

    @Test
    @DisplayName("플레이어가 블랙잭 패배할 경우 승리를 반환한다")
    void 플레이어가_블랙잭_패배할_경우_테스트() {
        //given
        dealer.receiveCard(new Card(CardRank.ACE, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.JACK, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.THREE, CardSuit.DIAMOND));

        //when
        WinningResults winningResults = WinningResults.of(players, dealer);
        Map<Player, GameResult> gameResult = winningResults.getResults();
        GameResult expect = GameResult.LOSE;

        //then
        assertEquals(gameResult.get(player), expect);
    }

    @Test
    @DisplayName("플레이어과 딜러가 동시에 블랙잭일 경우 무승부를 반환한다")
    void 동시에_블랙잭일때_무승부_테스트() {
        //given
        dealer.receiveCard(new Card(CardRank.ACE, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.JACK, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.ACE, CardSuit.CLOVER));
        player.receiveCard(new Card(CardRank.QUEEN, CardSuit.DIAMOND));

        //when
        WinningResults winningResults = WinningResults.of(players, dealer);
        Map<Player, GameResult> gameResult = winningResults.getResults();
        GameResult expect = GameResult.DRAW;

        //then
        assertEquals(gameResult.get(player), expect);
    }
}