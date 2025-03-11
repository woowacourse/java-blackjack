package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import model.card.Card;
import model.card.CardRank;
import model.card.CardSuit;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantWinningResultTest {
    private Dealer dealer;
    private Player player;
    private Players players;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        player = new Player("moda");
        players = new Players(List.of(player));
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
        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        GameResult result = participantWinningResult.getResult().get(player);
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
        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        GameResult result = participantWinningResult.getResult().get(player);
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
        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        GameResult result = participantWinningResult.getResult().get(player);
        GameResult expect = GameResult.LOSE;

        //then
        assertEquals(expect, result);
    }

    @Test
    @DisplayName("플레이어가 승리할 경우 승리를 반환한다")
    void 플레이어가_승리할_경우_테스트() {
        //given
        dealer.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.THREE, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));

        //when
        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        Map<Player, GameResult> gameResult = participantWinningResult.getResult();
        GameResult expect = GameResult.WIN;

        //then
        assertEquals(gameResult.get(player), expect);
    }

    @Test
    @DisplayName("플레이어가 패배할 경우 패배를 반환한다.")
    void 플레이어가_패배할_경우_테스트() {
        //given
        dealer.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.THREE, CardSuit.DIAMOND));

        //when
        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        Map<Player, GameResult> gameResult = participantWinningResult.getResult();
        GameResult expect = GameResult.LOSE;

        //then
        assertEquals(gameResult.get(player), expect);
    }

    @Test
    @DisplayName("플레이어가 무승부일 경우 무승부를 반환한다.")
    void 플레이어가_무승부일_경우_테스트() {
        //given
        dealer.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.TWO, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.FOUR, CardSuit.DIAMOND));

        //when
        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        Map<Player, GameResult> gameResult = participantWinningResult.getResult();
        GameResult expect = GameResult.DRAW;

        //then
        assertEquals(gameResult.get(player), expect);
    }

    @Test
    @DisplayName("딜러의 최종 승패를 결정한다.")
    void 딜러의_최종_승패를_결정한다() {
        //given
        GameResult playerResult1 = GameResult.WIN;
        GameResult playerResult2 = GameResult.DRAW;
        Map<Player, GameResult> playerGameResult =
                Map.of(new Player("a"), playerResult1, new Player("b"), playerResult2);
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(playerGameResult);

        //when
        Map<GameResult, Integer> dealerResults = participantWinningResult.decideDealerWinning();
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

    @Test
    @DisplayName("2장의 카드로 21을 만들어 승리할 경우 블랙잭을 반환한다")
    void 두장의_카드로_21을_만들어_승리할_경우_블랙잭을_반환한다() {
        //given
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.EIGHT, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.ACE, CardSuit.CLOVER));
        player.receiveCard(new Card(CardRank.KING, CardSuit.CLOVER));
        //when
        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        Map<Player, GameResult> gameResult = participantWinningResult.getResult();
        GameResult expect = GameResult.BLACKJACK;

        //then
        assertEquals(gameResult.get(player), expect);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 블랙잭일 경우 무승부 반환")
    void 딜러와_플레이어_모두_블랙잭일_경우_무승부를_반환() {
        //given
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.DIAMOND));
        dealer.receiveCard(new Card(CardRank.ACE, CardSuit.DIAMOND));
        player.receiveCard(new Card(CardRank.ACE, CardSuit.CLOVER));
        player.receiveCard(new Card(CardRank.KING, CardSuit.CLOVER));
        //when
        ParticipantWinningResult participantWinningResult = ParticipantWinningResult.of(players, dealer);
        Map<Player, GameResult> gameResult = participantWinningResult.getResult();
        GameResult expect = GameResult.DRAW;

        //then
        assertEquals(gameResult.get(player), expect);
    }
}