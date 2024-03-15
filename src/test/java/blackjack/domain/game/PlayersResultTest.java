package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import fixture.DealerFixture;
import fixture.PlayerFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersResultTest {

    @DisplayName("딜러의 승리 횟수는 플레이어들의 패배 횟수의 합이다.")
    @Test
    void testDealerWins() {
        // given
        Dealer dealer = DealerFixture.createDealer2();

        Player pobi = PlayerFixture.createPobi2();
        Player jason = PlayerFixture.createJason2();

        Players players = new Players(List.of(pobi, jason));

        // when
        PlayersResult playersResult = players.judge(dealer);

        // then
        assertThat(playersResult.dealerWins()).isEqualTo(1);
    }

    @DisplayName("딜러의 패배 횟수는 플레이어들의 승리 횟수의 합이다.")
    @Test
    void testDealerLosses() {
        // given
        Dealer dealer = DealerFixture.createDealer2();

        Player pobi = PlayerFixture.createPobi2();
        Player jason = PlayerFixture.createJason2();

        Players players = new Players(List.of(pobi, jason));

        // when
        PlayersResult playersResult = players.judge(dealer);

        // then
        assertThat(playersResult.dealerLosses()).isEqualTo(1);
    }

    @DisplayName("딜러의 무승부 횟수는 플레이어들의 무승부 횟수의 합이다.")
    @Test
    void dealerTies() {
        // given
        Dealer dealer = DealerFixture.createDealer2();

        Player pobi = PlayerFixture.createPobi2();
        Player jason = PlayerFixture.createJason2();

        Players players = new Players(List.of(pobi, jason));

        // when
        PlayersResult playersResult = players.judge(dealer);

        // then
        assertThat(playersResult.dealerTies()).isEqualTo(0);
    }
}
