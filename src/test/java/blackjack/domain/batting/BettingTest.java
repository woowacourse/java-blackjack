package blackjack.domain.batting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;
import static blackjack.domain.Fixture.*;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.result.GameResult;
import blackjack.domain.state.StateFactory;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingTest {

    private Betting betting;

    @BeforeEach
    void setUp() {
        betting = new Betting();
    }

    @DisplayName("한번 베팅한 게이머는 다시 베팅할 수 없다.")
    @Test
    void duplicate_betting_test() {
        // given
        Gamer gamer1 = new Gamer("pobi", StateFactory.generateState(FIXTURE_KING, FIXTURE_SEVEN));

        // when
        betting.betMoney(gamer1, 10_000);

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            betting.betMoney(gamer1, 10_000);
        });
    }

    @DisplayName("베팅 결과 테스트 - 게이머1 -> 10_000 (승) -> 10_000(수익) , 게이머2 -> 20_000 (패) -> -20_000(수익)")
    @Test
    void profitTest_1() {
        // given
        Gamer gamer1 = new Gamer("pobi", StateFactory.generateState(FIXTURE_TWO, FIXTURE_SEVEN));
        gamer1.addCard(FIXTURE_ACE);
        gamer1.stay();

        Gamer gamer2 = new Gamer("jason", StateFactory.generateState(FIXTURE_SIX, FIXTURE_KING));
        gamer2.stay();

        Dealer dealer = new Dealer(StateFactory.generateState(FIXTURE_THREE, FIXTURE_NINE));
        dealer.addCard(FIXTURE_SEVEN);
        dealer.stay();

        // when
        betting.betMoney(gamer1, 10_000);
        betting.betMoney(gamer2, 20_000);
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer1, gamer2));
        BettingResult bettingResult = betting.calculateGamersProfit(gameResult);

        // then
        assertThat(bettingResult.getGamersProfit().get(gamer1)).isEqualTo(10_000);
        assertThat(bettingResult.getGamersProfit().get(gamer2)).isEqualTo(-20_000);
        assertThat(bettingResult.getDealerProfit()).isEqualTo(10_000);
    }

    @DisplayName("베팅 결과 테스트 - 게이머1 -> 10_000 (블랙잭 승) -> 15_000(수익)")
    @Test
    void profitTest_2() {
        // given
        Gamer gamer1 = new Gamer("pobi", StateFactory.generateState(FIXTURE_KING, FIXTURE_ACE));
        gamer1.stay();

        Dealer dealer = new Dealer(StateFactory.generateState(FIXTURE_THREE, FIXTURE_NINE));
        dealer.addCard(FIXTURE_SEVEN);
        dealer.stay();

        // when
        betting.betMoney(gamer1, 10_000);
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer1));
        BettingResult bettingResult = betting.calculateGamersProfit(gameResult);

        // then
        assertThat(bettingResult.getGamersProfit().get(gamer1)).isEqualTo(15_000);
        assertThat(bettingResult.getDealerProfit()).isEqualTo(-15_000);
    }

    @DisplayName("베팅 결과 테스트 - 게이머1 -> 10_000 (패) -> 10_000(수익) , 딜러 (블랙잭 승)")
    @Test
    void profitTest_3() {
        // given
        Gamer gamer1 = new Gamer("pobi", StateFactory.generateState(FIXTURE_TWO, FIXTURE_SEVEN));
        gamer1.stay();

        Dealer dealer = new Dealer(StateFactory.generateState(FIXTURE_KING, FIXTURE_ACE));
        dealer.stay();

        // when
        betting.betMoney(gamer1, 10_000);
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer1));
        BettingResult bettingResult = betting.calculateGamersProfit(gameResult);

        // then
        assertThat(bettingResult.getGamersProfit().get(gamer1)).isEqualTo(-10_000);
        assertThat(bettingResult.getDealerProfit()).isEqualTo(10_000);
    }

}