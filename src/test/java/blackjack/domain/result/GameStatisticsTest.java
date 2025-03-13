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

import static blackjack.domain.result.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

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

        statistics = GameStatistics.initialize(participants);
    }

    @Test
    @DisplayName("게임 참가자의 승패무와 같은 게임 결과를 기입할 수 있다. " +
            "외부에서 반대 측에 반대 결과를 기입해준다면, 실수의 여지가 있기에 1번의 호출로 양측에 결과를 기입한다 ")
    void canMarkGameResult() {
        // given
        // when
        statistics.markResult(player1, dealer, GameResult.WIN);
        statistics.markResult(player2, dealer, GameResult.DRAW);
        statistics.markResult(player3, dealer, GameResult.LOSE);

        Map<GameResult, Integer> dealerResults = GameResult.count(statistics.find(dealer));

        // then
        assertThat(statistics.getParticipantToResults().get(player1).getFirst()).isEqualTo(GameResult.WIN);
        assertThat(statistics.getParticipantToResults().get(player2).getFirst()).isEqualTo(GameResult.DRAW);
        assertThat(dealerResults.get(GameResult.WIN)).isEqualTo(1);
        assertThat(dealerResults.get(GameResult.LOSE)).isEqualTo(1);
        assertThat(dealerResults.get(GameResult.DRAW)).isEqualTo(1);
    }
}
