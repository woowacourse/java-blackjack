package model.winning;

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
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        WinningResult result = participantWinningResult.getResult().get(player);
        WinningResult expect = WinningResult.WIN;

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
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        WinningResult result = participantWinningResult.getResult().get(player);
        WinningResult expect = WinningResult.LOSE;

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
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        WinningResult result = participantWinningResult.getResult().get(player);
        WinningResult expect = WinningResult.LOSE;

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
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        Map<Player, WinningResult> gameResult = participantWinningResult.getResult();
        WinningResult expect = WinningResult.WIN;

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
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        Map<Player, WinningResult> gameResult = participantWinningResult.getResult();
        WinningResult expect = WinningResult.LOSE;

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
        ParticipantWinningResult participantWinningResult = new ParticipantWinningResult(players, dealer);
        Map<Player, WinningResult> gameResult = participantWinningResult.getResult();
        WinningResult expect = WinningResult.DRAW;

        //then
        assertEquals(gameResult.get(player), expect);
    }
}