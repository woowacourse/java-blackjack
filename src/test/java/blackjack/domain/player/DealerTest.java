package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static blackjack.domain.Fixture.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.ResultType;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러 점수가 16점이 초과되면 카드를 추가할 수 없다.")
    @Test
    void 카드_추가_테스트() {
        // given, when
        State state = StateFactory.generateState(FIXTURE_KING, FIXTURE_SEVEN);
        Player dealer = new Dealer(state);
        System.out.println(dealer.calculateScore());

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            dealer.addCard(FIXTURE_FIVE);
        });
    }

    @DisplayName("딜러와 플레이어들의 게임 결과 테스트 - 딜러 1승 1패")
    @Test
    void judgeGameResultWithGamers_1() {
        // given
        Gamer gamer1 = new Gamer("pobi", StateFactory.generateState(FIXTURE_KING, FIXTURE_SEVEN));

        Gamer gamer2 = new Gamer("jason", StateFactory.generateState(FIXTURE_KING, FIXTURE_TWO));

        Dealer dealer = new Dealer(StateFactory.generateState(FIXTURE_KING, FIXTURE_SIX));

        // when
        GameResult gameResult = dealer.judgeGameResultWithGamers(Arrays.asList(gamer1, gamer2));

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
        GameResult gameResult = dealer.judgeGameResultWithGamers(Arrays.asList(gamer1, gamer2));

        // then
        assertThat(gameResult.findResultByGamer(gamer1)).isSameAs(ResultType.DRAW);
        assertThat(gameResult.findResultByGamer(gamer2)).isSameAs(ResultType.DRAW);
        assertThat(gameResult.getDealerResult()).containsExactly(ResultType.DRAW, ResultType.DRAW);
    }
}
