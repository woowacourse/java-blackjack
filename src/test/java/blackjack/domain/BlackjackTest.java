package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.player.Names;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Score;
import blackjack.domain.result.GamePlayerResult;
import blackjack.domain.result.ResultStatus;
import blackjack.fixture.CardFixture;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("이름 목록을 통해 플레이어를 생성하고 딜러와 플레이어에게 카드를 두장씩 나눠준다.")
    void accept_players() {
        final Blackjack blackjack = new Blackjack(CardFixture.카드_덱_생성());
        final Names names = Names.from(List.of("초롱", "조이썬"));

        blackjack.acceptPlayers(names);

        assertPlayer(blackjack.getDealer(), 8);

        assertPlayer(blackjack.getGamePlayers()
                              .get(0), 13);
        assertPlayer(blackjack.getGamePlayers()
                              .get(1), 15);
    }

    @Test
    @DisplayName("딜러가 게임 결과를 종합한다.")
    void dealer_Count_result() {
        final var names = Names.from(List.of("초롱", "조이썬"));
        final var sut = new Blackjack(CardFixture.카드_덱_생성());
        sut.acceptPlayers(names);

        final var result = sut.checkPlayersResult();

        assertGamePlayerResult(result.getGamePlayerResults()
                                     .get(0), ResultStatus.WIN);

        assertGamePlayerResult(result.getGamePlayerResults()
                                     .get(1), ResultStatus.WIN);

        assertThat(result.getDealerResult()
                         .getCountWithResultStatus(ResultStatus.WIN)).isZero();
    }

    private void assertPlayer(final Participant participant, final int value) {
        assertThat(participant.calculateScore()).isEqualTo(Score.from(value));
    }

    private void assertGamePlayerResult(final GamePlayerResult gamePlayerResult, ResultStatus resultStatus) {
        assertThat(gamePlayerResult.getResultStatus()).isEqualTo(resultStatus);
    }

}
