package blackjack.domain.result;

import static blackjack.domain.Fixture.FIXTURE_KING;
import static blackjack.domain.Fixture.FIXTURE_SEVEN;
import static blackjack.domain.Fixture.FIXTURE_SIX;
import static blackjack.domain.Fixture.FIXTURE_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.state.StateFactory;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @DisplayName("딜러와 플레이어들의 게임 결과 테스트 - 딜러 1승 1패")
    @Test
    void judgeGameResultWithGamers_1() {
        // given
        Gamer gamer1 = new Gamer("pobi", StateFactory.generateState(FIXTURE_KING, FIXTURE_SEVEN));

        Gamer gamer2 = new Gamer("jason", StateFactory.generateState(FIXTURE_KING, FIXTURE_TWO));

        Dealer dealer = new Dealer(StateFactory.generateState(FIXTURE_KING, FIXTURE_SIX));

        // when
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer1, gamer2));

        // then
        assertThat(gameResult.findResultByGamer(gamer1)).isSameAs(ResultType.WIN);
        assertThat(gameResult.findResultByGamer(gamer2)).isSameAs(ResultType.LOSE);
        assertThat(gameResult.getDealerResult()).containsExactly(ResultType.LOSE, ResultType.WIN);
    }

    @DisplayName("딜러와 플레이어들의 게임 결과 테스트 - 딜러 2무")
    @Test
    void judgeGameResultWithGamers_2() {
        // given
        Gamer gamer1 = new Gamer("pobi", StateFactory.generateState(FIXTURE_KING, FIXTURE_SIX));

        Gamer gamer2 = new Gamer("jason", StateFactory.generateState(FIXTURE_KING, FIXTURE_SIX));

        Dealer dealer = new Dealer(StateFactory.generateState(FIXTURE_KING, FIXTURE_SIX));

        // when
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer1, gamer2));

        // then
        assertThat(gameResult.findResultByGamer(gamer1)).isSameAs(ResultType.DRAW);
        assertThat(gameResult.findResultByGamer(gamer2)).isSameAs(ResultType.DRAW);
        assertThat(gameResult.getDealerResult()).containsExactly(ResultType.DRAW, ResultType.DRAW);
    }

}