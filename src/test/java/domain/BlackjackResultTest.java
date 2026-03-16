package domain;

import static org.assertj.core.api.Assertions.*;

import domain.fixture.TestFixture;
import domain.shuffle.NoShuffleStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class BlackjackResultTest {

    @Test
    @DisplayName("Dealer 수익 정상 계산 여부를 판정한다.")
    void 딜러_수익_정상_계산_테스트(){
        // given
        Player player = TestFixture.player();
        Dealer dealer = TestFixture.dealer();
        Players players = Players.of();
        players.add(player);

        PlayerBets playerBets = PlayerBets.of();
        BetAmount betAmount = BetAmount.of("1000");
        playerBets.add(player, betAmount);

        BlackjackResult blackjackResult = BlackjackResult.of(players, dealer, playerBets);

        // when
        long dealerProfit = blackjackResult.dealerProfit();

        // then
        long expect = -1000L;
        assertThat(dealerProfit).isEqualTo(expect);
    }

    @Test
    @DisplayName("Player 수익 정상 계산 여부를 판정한다.")
    void 플레이어_수익_정상_계산_테스트(){
        // given
        Player player = TestFixture.player();
        Dealer dealer = TestFixture.dealer();
        Players players = Players.of();
        players.add(player);

        PlayerBets playerBets = PlayerBets.of();
        BetAmount betAmount = BetAmount.of("100");
        playerBets.add(player, betAmount);

        BlackjackResult blackjackResult = BlackjackResult.of(players, dealer, playerBets);

        // when
        long playerProfit = blackjackResult.playerProfits().get(player);

        // then
        long expect = 100L;
        assertThat(playerProfit).isEqualTo(expect);
    }

}
