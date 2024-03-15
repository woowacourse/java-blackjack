package domain.game;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerResultsTest {

    private Player player;
    private Dealer dealer;
    private PlayerResults playerResults;

    @BeforeEach
    void beforeEach() {
        player = Player.withName("name");
        dealer = Dealer.withNoCards();
        playerResults = PlayerResults.withNoEntry();
    }

    @Test
    @DisplayName("무승부: 플레이어 점수(8점) == 딜러 점수(8점)")
    void tie_PlayerScoreIsEqualToDealerScore() {
        player.tryReceive(new Card(Rank.EIGHT, Symbol.DIAMOND));
        dealer.tryReceive(new Card(Rank.EIGHT, Symbol.HEART));
        playerResults.addResultOf(player, dealer);

        Assertions.assertThat(playerResults.resultOf(player)).isEqualTo(PlayerResult.TIE);
    }

    @Test
    @DisplayName("무승부: 플레이어 점수(블랙잭) == 딜러 점수(블랙잭)")
    void tie_PlayerBlackjack_DealerBlackjack() {
        player.tryReceive(List.of(
            new Card(Rank.TEN, Symbol.HEART),
            new Card(Rank.ACE, Symbol.HEART)
        ));
        dealer.tryReceive(List.of(
            new Card(Rank.TEN, Symbol.CLUB),
            new Card(Rank.ACE, Symbol.CLUB)
        ));

        playerResults.addResultOf(player, dealer);

        Assertions.assertThat(playerResults.resultOf(player)).isEqualTo(PlayerResult.TIE);
    }

    @Test
    @DisplayName("플레이어 BLACKJACK: 플레이어 블랙잭, 딜러 블랙잭 아닌 21점")
    void tie_PlayerBlackjack_DealerTwentyOne() {
        player.tryReceive(List.of(
            new Card(Rank.TEN, Symbol.HEART),
            new Card(Rank.ACE, Symbol.HEART)
        ));
        dealer.tryReceive(List.of(
            new Card(Rank.TEN, Symbol.CLUB),
            new Card(Rank.NINE, Symbol.CLUB),
            new Card(Rank.THREE, Symbol.CLUB)
        ));

        playerResults.addResultOf(player, dealer);

        Assertions.assertThat(playerResults.resultOf(player)).isEqualTo(PlayerResult.BLACKJACK);
    }

    @Test
    @DisplayName("플레이어 패배: 플레이어 점수 < 딜러 점수")
    void playerLose_PlayerScoreIsLessThanDealerScore() {
        player.tryReceive(new Card(Rank.EIGHT, Symbol.DIAMOND));
        dealer.tryReceive(new Card(Rank.NINE, Symbol.HEART));

        playerResults.addResultOf(player, dealer);

        Assertions.assertThat(playerResults.resultOf(player)).isEqualTo(PlayerResult.LOSE);
    }

    @Test
    @DisplayName("플레이어 승리: 플레이어 점수 > 딜러 점수")
    void playerWin_PlayerScoreIsGreaterThanDealerScore() {
        player.tryReceive(new Card(Rank.NINE, Symbol.DIAMOND));
        dealer.tryReceive(new Card(Rank.EIGHT, Symbol.HEART));

        playerResults.addResultOf(player, dealer);

        Assertions.assertThat(playerResults.resultOf(player)).isEqualTo(PlayerResult.WIN);
    }

    @Test
    @DisplayName("플레이어 패배: 플레이어 버스트(경계값 22점)")
    void playerLose_PlayerIsBusted() {
        player.tryReceive(new Card(Rank.KING, Symbol.DIAMOND));
        player.tryReceive(new Card(Rank.QUEEN, Symbol.CLUB));
        player.tryReceive(new Card(Rank.TWO, Symbol.HEART));
        dealer.tryReceive(new Card(Rank.KING, Symbol.HEART));

        playerResults.addResultOf(player, dealer);

        Assertions.assertThat(playerResults.resultOf(player)).isEqualTo(PlayerResult.LOSE);
    }

    @Test
    @DisplayName("플레이어 승리: 딜러 버스트(경계값 22점), 플레이어 생존")
    void playerWin_DealerIsBustedAndPlayerIsNotBusted() {
        dealer.tryReceive(new Card(Rank.KING, Symbol.DIAMOND));
        dealer.tryReceive(new Card(Rank.SIX, Symbol.CLUB));
        dealer.tryReceive(new Card(Rank.SIX, Symbol.HEART));
        player.tryReceive(new Card(Rank.KING, Symbol.HEART));

        playerResults.addResultOf(player, dealer);

        Assertions.assertThat(playerResults.resultOf(player)).isEqualTo(PlayerResult.WIN);
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

        playerResults.addResultOf(player, dealer);

        Assertions.assertThat(playerResults.resultOf(player)).isEqualTo(PlayerResult.LOSE);
    }
}
