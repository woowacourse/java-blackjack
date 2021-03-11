package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static blackjack.domain.Fixture.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import blackjack.domain.state.StateFactory;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTypeTest {

    @DisplayName("두 플레이어의 게임 결과 테스트 - 플레이어 패")
    @Test
    void judgeGameResultTest_1() {
        // given
        Gamer gamer = new Gamer("pobi", StateFactory.generateState(FIXTURE_KING, FIXTURE_SEVEN));

        Dealer dealer = new Dealer(StateFactory.generateState(FIXTURE_KING, FIXTURE_NINE));

        // when
        ResultType result = ResultType.judgeGameResult(dealer, gamer);

        // then
        assertThat(result).isSameAs(ResultType.LOSE);
    }

    @DisplayName("두 플레이어의 게임 결과 테스트 - 플레이어 승")
    @Test
    void judgeGameResultTest_2() {
        // given
        Gamer gamer = new Gamer("pobi", StateFactory.generateState(FIXTURE_KING, FIXTURE_KING));

        Dealer dealer = new Dealer(StateFactory.generateState(FIXTURE_KING, FIXTURE_NINE));

        // when
        ResultType result = ResultType.judgeGameResult(dealer, gamer);

        // then
        assertThat(result).isSameAs(ResultType.WIN);
    }

    @DisplayName("두 플레이어의 게임 결과 테스트 - 플레이어 딜러 모두 블랙잭 무")
    @Test
    void judgeGameResultTest_3() {
        // given
        Gamer gamer = new Gamer("pobi", StateFactory.generateState(FIXTURE_KING, FIXTURE_ACE));

        Dealer dealer = new Dealer(StateFactory.generateState(FIXTURE_KING, FIXTURE_ACE));

        // when
        ResultType result = ResultType.judgeGameResult(dealer, gamer);

        // then
        assertThat(result).isSameAs(ResultType.DRAW);
    }

    @DisplayName("두 플레이어의 게임 결과 테스트 - 플레이어 딜러 모두 블랙잭이 아닌 21 무")
    @Test
    void judgeGameResultTest_4() {
        // given
        Gamer gamer = new Gamer("pobi", StateFactory.generateState(FIXTURE_KING, FIXTURE_KING));

        Dealer dealer = new Dealer(StateFactory.generateState(FIXTURE_KING, FIXTURE_FIVE));

        // when
        gamer.addCard(FIXTURE_ACE);
        dealer.addCard(FIXTURE_SIX);

        ResultType result = ResultType.judgeGameResult(dealer, gamer);

        // then
        assertThat(result).isSameAs(ResultType.DRAW);
    }

    @DisplayName("두 플레이어의 게임 결과 테스트 - 딜러 버스트, 플레이어 승")
    @Test
    void judgeGameResultTest_5() {
        // given
        Gamer gamer = new Gamer("pobi", StateFactory.generateState(FIXTURE_KING, FIXTURE_KING));

        Dealer dealer = new Dealer(StateFactory.generateState(FIXTURE_KING, FIXTURE_FIVE));
        dealer.addCard(FIXTURE_KING);

        // when
        ResultType result = ResultType.judgeGameResult(dealer, gamer);

        // then
        assertThat(result).isSameAs(ResultType.WIN);
    }

    @DisplayName("두 플레이어의 게임 결과 테스트 - 딜러 승, 플레이어 버스트")
    @Test
    void judgeGameResultTest_6() {
        // given
        Gamer gamer = new Gamer("pobi", StateFactory.generateState(FIXTURE_KING, FIXTURE_KING));
        gamer.addCard(FIXTURE_KING);

        Dealer dealer = new Dealer(StateFactory.generateState(FIXTURE_KING, FIXTURE_NINE));

        // when
        ResultType result = ResultType.judgeGameResult(dealer, gamer);

        // then
        assertThat(result).isSameAs(ResultType.LOSE);
    }

    @DisplayName("두 플레이어의 게임 결과 테스트 - 딜러 버스트, 플레이어 버스트 => 플레이어 승")
    @Test
    void judgeGameResultTest_7() {
        // given
        Gamer gamer = new Gamer("pobi", StateFactory.generateState(FIXTURE_KING, FIXTURE_KING));
        gamer.addCard(FIXTURE_KING);

        Dealer dealer = new Dealer(StateFactory.generateState(FIXTURE_KING, FIXTURE_FIVE));
        dealer.addCard(FIXTURE_KING);

        // when
        ResultType result = ResultType.judgeGameResult(dealer, gamer);

        // then
        assertThat(result).isSameAs(ResultType.WIN);
    }
}
