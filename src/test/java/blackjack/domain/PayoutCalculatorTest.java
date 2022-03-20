package blackjack.domain;

import static blackjack.domain.Fixtures.ACE;
import static blackjack.domain.Fixtures.EIGHT;
import static blackjack.domain.Fixtures.NINE;
import static blackjack.domain.Fixtures.TEN;
import static blackjack.domain.Fixtures.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.human.Dealer;
import blackjack.domain.result.PayoutCalculator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PayoutCalculatorTest {

    @Nested
    @DisplayName("플레이어 1.5배 우승 결과 테스트")
    class PlayerWinMaxTest {
        private final Fixtures fx = new Fixtures();

        @Test
        void resultTest() {
            fx.POBI.addCard(ACE);
            fx.POBI.addCard(TEN);
            Dealer dealer = new Dealer(List.of(TEN, NINE, TEN));

            assertThat(PayoutCalculator.compute(fx.POBI, dealer))
                    .isEqualTo(15000);
        }
    }

    @Nested
    @DisplayName("플레이어 2장블랙잭/딜러블랙잭 무승부 결과 테스트")
    class PlayerWinMax2Test {
        private final Fixtures fx = new Fixtures();

        @Test
        void resultTest() {
            fx.POBI.addCard(ACE);
            fx.POBI.addCard(TEN);
            Dealer dealer = new Dealer(List.of(ACE, TEN));

            assertThat(PayoutCalculator.compute(fx.POBI, dealer))
                    .isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("딜러 버스트 결과 테스트")
    class DealerOverTest {
        private final Fixtures fx = new Fixtures();

        @Test
        void resultTest() {
            fx.POBI.addCard(TWO);
            fx.POBI.addCard(TEN);
            fx.POBI.setStay();
            Dealer dealer = new Dealer(
                    List.of(TEN, NINE, EIGHT));
            assertThat(PayoutCalculator.compute(fx.POBI, dealer))
                    .isEqualTo(10000);
        }
    }

    @Nested
    @DisplayName("딜러 21이하, 플레이어 21이하 결과 딜러승리 테스트")
    class DealerPlayerUnder1Test {
        private final Fixtures fx = new Fixtures();

        @Test
        void resultTest() {
            fx.POBI.addCard(TWO);
            fx.POBI.addCard(TEN);
            fx.POBI.setStay();
            Dealer dealer = new Dealer(List.of(TEN, NINE, ACE));
            assertThat(PayoutCalculator.compute(fx.POBI, dealer))
                    .isEqualTo(-10000);
        }
    }

    @Nested
    @DisplayName("딜러 21이하, 플레이어 21이하 결과 플레이어 승리 테스트")
    class DealerPlayerUnder2Test {
        private final Fixtures fx = new Fixtures();

        @Test
        void resultTest() {
            fx.POBI.addCard(TEN);
            fx.POBI.addCard(TEN);
            fx.POBI.setStay();
            Dealer dealer = new Dealer(List.of(ACE, ACE));
            assertThat(PayoutCalculator.compute(fx.POBI, dealer))
                    .isEqualTo(10000);
        }
    }

    @Nested
    @DisplayName("딜러 21이하, 플레이어 21이하 결과 무승부 테스트")
    class DealerPlayerUnder3Test {
        private final Fixtures fx = new Fixtures();

        @Test
        void resultTest() {
            fx.POBI.addCard(ACE);
            fx.POBI.addCard(ACE);
            fx.POBI.setStay();
            Dealer dealer = new Dealer(List.of(ACE, ACE));
            assertThat(PayoutCalculator.compute(fx.POBI, dealer))
                    .isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("딜러 21이하, 플레이어 버스트 결과 테스트")
    class DealerUnderPlayerHighTest {
        private final Fixtures fx = new Fixtures();

        @Test
        void resultTest() {
            fx.POBI.addCard(TWO);
            fx.POBI.addCard(TEN);
            fx.POBI.addCard(TEN);
            Dealer dealer = new Dealer(List.of(ACE, NINE));
            assertThat(PayoutCalculator.compute(fx.POBI, dealer))
                    .isEqualTo(-10000);
        }
    }
}
