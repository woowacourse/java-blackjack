package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer2;
import blackjack.domain.participant.Player2;
import blackjack.domain.participant.Players2;
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
        Dealer2 dealer = DealerFixture.createDealer2();

        Player2 pobi = PlayerFixture.createPobi2();
        Player2 jason = PlayerFixture.createJason2();

        Players2 players = new Players2(List.of(pobi, jason));

        // when
        PlayersResult playersResult = players.judge(dealer);

        // then
        assertThat(playersResult.dealerWins()).isEqualTo(1);
    }

    @DisplayName("딜러의 패배 횟수는 플레이어들의 승리 횟수의 합이다.")
    @Test
    void testDealerLosses() {
        // given
        Dealer2 dealer = DealerFixture.createDealer2();

        Player2 pobi = PlayerFixture.createPobi2();
        Player2 jason = PlayerFixture.createJason2();

        Players2 players = new Players2(List.of(pobi, jason));

        // when
        PlayersResult playersResult = players.judge(dealer);

        // then
        assertThat(playersResult.dealerLosses()).isEqualTo(1);
    }

    @DisplayName("딜러의 무승부 횟수는 플레이어들의 무승부 횟수의 합이다.")
    @Test
    void dealerTies() {
        // given
        Dealer2 dealer = DealerFixture.createDealer2();

        Player2 pobi = PlayerFixture.createPobi2();
        Player2 jason = PlayerFixture.createJason2();

        Players2 players = new Players2(List.of(pobi, jason));

        // when
        PlayersResult playersResult = players.judge(dealer);

        // then
        assertThat(playersResult.dealerTies()).isEqualTo(0);
    }
}
