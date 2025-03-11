package blackjack.domain.result;

import blackjack.domain.card.CardFixture;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.GameParticipantFixture;
import blackjack.domain.gamer.GameParticipants;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameStatisticsTest {

    Player player1; // 20점 (승)
    Player player2; // 18점 (무)
    Player player3; // 16점 (패)
    Dealer dealer; // 18점 (1승 1패 1무)
    GameParticipants participants;
    GameStatistics statistics;

    @BeforeEach
    void setUp() {
        player1 = GameParticipantFixture.createPlayer("강산");
        player2 = GameParticipantFixture.createPlayer("재중");
        player3 = GameParticipantFixture.createPlayer("아현");
        dealer = GameParticipantFixture.createDealer();

        participants = GameParticipants.of(List.of(player1, player2, player3), dealer);
        player1.drawCard(CardFixture.createCard(10));
        player1.drawCard(CardFixture.createCard(10));

        player2.drawCard(CardFixture.createCard(9));
        player2.drawCard(CardFixture.createCard(9));

        player3.drawCard(CardFixture.createCard(8));
        player3.drawCard(CardFixture.createCard(8));

        dealer.drawCard(CardFixture.createCard(9));
        dealer.drawCard(CardFixture.createCard(9));

        statistics = GameStatistics.from(participants);
    }

    @Test
    @DisplayName("게임 참가자로부터 GameStatistics를 생성할 수 있다")
    void canCreateGameStatisticsFromGameParticipants() {
        // given
        // when
        // then
        assertAll(() -> {
            assertThat(statistics.findOriginSumOfCards(player1)).isEqualTo(20);
            assertThat(statistics.findOriginSumOfCards(player2)).isEqualTo(18);
            assertThat(statistics.findOriginSumOfCards(player3)).isEqualTo(16);
            assertThat(statistics.findOriginSumOfCards(dealer)).isEqualTo(18);
        });
    }

    @Test
    @DisplayName("플레이어의 승패를 결정할 수 있다")
    void canDecidePlayerResults() {
        // given
        // when
        Map<Player, GameResult> results = statistics.decidePlayerResults();

        // then
        assertAll(() -> {
            assertThat(results.get(player1)).isEqualTo(GameResult.WIN);
            assertThat(results.get(player2)).isEqualTo(GameResult.DRAW);
            assertThat(results.get(player3)).isEqualTo(GameResult.LOSE);
        });
    }

    @Test
    @DisplayName("딜러의 게임 결과를 계산할 수 있다")
    void canCalculateDealerResult() {
        // given
        Map<Player, GameResult> playerResults = statistics.decidePlayerResults();

        // when
        Map<GameResult, Integer> dealerResult = statistics.calculateDealerResult(playerResults);

        // then
        assertAll(() -> {
            assertThat(dealerResult.get(GameResult.LOSE)).isEqualTo(1);
            assertThat(dealerResult.get(GameResult.WIN)).isEqualTo(1);
            assertThat(dealerResult.get(GameResult.DRAW)).isEqualTo(1);
        });
    }
}
