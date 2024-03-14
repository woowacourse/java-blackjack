package domain.game;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    private Player player;
    private Dealer dealer;

    @BeforeEach
    void beforeEach() {
        player = Player.withName("name");
        dealer = Dealer.withNoCards();
    }

    @Test
    @DisplayName("무승부: 플레이어 점수 == 딜러 점수")
    void tie_PlayerScoreIsEqualToDealerScore() {
        player.tryReceive(new Card(Rank.EIGHT, Symbol.DIAMOND));
        dealer.tryReceive(new Card(Rank.EIGHT, Symbol.HEART));

        Result result = Result.of(List.of(player), dealer);

        Assertions.assertThat(result.playerWinLose(player)).isEqualTo(WinLose.TIE);
    }

    @Test
    @DisplayName("플레이어 패배: 플레이어 점수 < 딜러 점수")
    void playerLose_PlayerScoreIsLessThanDealerScore() {
        player.tryReceive(new Card(Rank.EIGHT, Symbol.DIAMOND));
        dealer.tryReceive(new Card(Rank.NINE, Symbol.HEART));

        Result result = Result.of(List.of(player), dealer);

        Assertions.assertThat(result.playerWinLose(player)).isEqualTo(WinLose.LOSE);
    }

    @Test
    @DisplayName("플레이어 승리: 플레이어 점수 > 딜러 점수")
    void playerWin_PlayerScoreIsGreaterThanDealerScore() {
        player.tryReceive(new Card(Rank.NINE, Symbol.DIAMOND));
        dealer.tryReceive(new Card(Rank.EIGHT, Symbol.HEART));

        Result result = Result.of(List.of(player), dealer);

        Assertions.assertThat(result.playerWinLose(player)).isEqualTo(WinLose.WIN);
    }

    @Test
    @DisplayName("플레이어 패배: 플레이어 버스트(경계값 22점)")
    void playerLose_PlayerIsBusted() {
        player.tryReceive(new Card(Rank.KING, Symbol.DIAMOND));
        player.tryReceive(new Card(Rank.QUEEN, Symbol.CLUB));
        player.tryReceive(new Card(Rank.TWO, Symbol.HEART));
        dealer.tryReceive(new Card(Rank.KING, Symbol.HEART));

        Result result = Result.of(List.of(player), dealer);

        Assertions.assertThat(result.playerWinLose(player)).isEqualTo(WinLose.LOSE);
    }

    @Test
    @DisplayName("플레이어 승리: 딜러 버스트(경계값 22점), 플레이어 생존")
    void playerWin_DealerIsBustedAndPlayerIsNotBusted() {
        dealer.tryReceive(new Card(Rank.KING, Symbol.DIAMOND));
        dealer.tryReceive(new Card(Rank.SIX, Symbol.CLUB));
        dealer.tryReceive(new Card(Rank.SIX, Symbol.HEART));
        player.tryReceive(new Card(Rank.KING, Symbol.HEART));

        Result result = Result.of(List.of(player), dealer);

        Assertions.assertThat(result.playerWinLose(player)).isEqualTo(WinLose.WIN);
    }

    @Test
    @DisplayName("플레이어 패배: 딜러 버스트(22점), 플레이어 버스트(22점)")
    void playerLose_DealerIsBustedAndPlayerIsBusted() {
        dealer.tryReceive(new Card(Rank.KING, Symbol.DIAMOND));
        dealer.tryReceive(new Card(Rank.SIX, Symbol.CLUB));
        dealer.tryReceive(new Card(Rank.SIX, Symbol.HEART));

        player.tryReceive(new Card(Rank.KING, Symbol.HEART));
        player.tryReceive(new Card(Rank.KING, Symbol.CLUB));
        player.tryReceive(new Card(Rank.TWO, Symbol.HEART));

        Result result = Result.of(List.of(player), dealer);

        Assertions.assertThat(result.playerWinLose(player)).isEqualTo(WinLose.LOSE);
    }

    @Test
    @DisplayName("딜러의 승리 횟수를 셀 수 있다.")
    void dealerWinCount() {
        Player player1 = Player.withName("user1");
        Player player2 = Player.withName("user2");
        Dealer dealer = Dealer.withNoCards();

        player1.tryReceive(new Card(Rank.TWO, Symbol.DIAMOND));
        player2.tryReceive(new Card(Rank.TWO, Symbol.SPADE));
        dealer.tryReceive(new Card(Rank.KING, Symbol.HEART));
        Result result = Result.of(List.of(player1, player2), dealer);

        Assertions.assertThat(result.dealerWinCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 패배 횟수를 셀 수 있다.")
    void dealerLoseCount() {
        Player player1 = Player.withName("user1");
        Player player2 = Player.withName("user2");
        Dealer dealer = Dealer.withNoCards();

        player1.tryReceive(new Card(Rank.KING, Symbol.DIAMOND));
        player2.tryReceive(new Card(Rank.KING, Symbol.SPADE));
        dealer.tryReceive(new Card(Rank.TWO, Symbol.HEART));
        Result result = Result.of(List.of(player1, player2), dealer);

        Assertions.assertThat(result.dealerLoseCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 무승부 횟수를 셀 수 있다.")
    void dealerTieCount() {
        Player player1 = Player.withName("user1");
        Player player2 = Player.withName("user2");
        Dealer dealer = Dealer.withNoCards();

        player1.tryReceive(new Card(Rank.KING, Symbol.DIAMOND));
        player2.tryReceive(new Card(Rank.KING, Symbol.SPADE));
        dealer.tryReceive(new Card(Rank.KING, Symbol.HEART));
        Result result = Result.of(List.of(player1, player2), dealer);

        Assertions.assertThat(result.dealerTieCount()).isEqualTo(2);
    }
}
