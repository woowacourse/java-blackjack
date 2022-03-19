package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.human.Dealer;
import blackjack.domain.result.PrideCalculator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PrideCalculatorTest {

    @Nested
    @DisplayName("플레이어 1.5배 우승 결과 테스트")
    class PlayerWinMaxTest {
        private final Fixture fx = new Fixture();

        @Test
        void resultTest() {
            fx.POBI.addCard(fx.ACE);
            fx.POBI.addCard(fx.TEN);
            Dealer dealer = new Dealer(List.of(fx.TEN, fx.NINE, fx.TEN));

            assertThat(PrideCalculator.compute(fx.POBI, dealer))
                    .isEqualTo(15000);
        }
    }

    @Nested
    @DisplayName("플레이어 2장블랙잭/딜러블랙잭 무승부 결과 테스트")
    class PlayerWinMax2Test {
        private final Fixture fx = new Fixture();

        @Test
        void resultTest() {
            fx.POBI.addCard(fx.ACE);
            fx.POBI.addCard(fx.TEN);
            Dealer dealer = new Dealer(List.of(fx.ACE, fx.TEN));

            assertThat(PrideCalculator.compute(fx.POBI, dealer))
                    .isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("딜러 버스트 결과 테스트")
    class DealerOverTest {
        private final Fixture fx = new Fixture();

        @Test
        void resultTest() {
            fx.POBI.addCard(fx.TWO);
            fx.POBI.addCard(fx.TEN);
            Dealer dealer = new Dealer(
                    List.of(fx.TEN, fx.NINE, fx.EIGHT));
            assertThat(PrideCalculator.compute(fx.POBI, dealer))
                    .isEqualTo(10000);
        }
    }

    @Nested
    @DisplayName("딜러 21이하, 플레이어 21이하 결과 딜러승리 테스트")
    class DealerPlayerUnder1Test {
        private final Fixture fx = new Fixture();

        @Test
        void resultTest() {
            fx.POBI.addCard(fx.TWO);
            fx.POBI.addCard(fx.TEN);
            Dealer dealer = new Dealer(List.of(fx.TEN, fx.NINE, fx.ACE));
            assertThat(PrideCalculator.compute(fx.POBI, dealer))
                    .isEqualTo(-10000);
        }
    }

    @Nested
    @DisplayName("딜러 21이하, 플레이어 21이하 결과 플레이어 승리 테스트")
    class DealerPlayerUnder2Test {
        private final Fixture fx = new Fixture();

        @Test
        void resultTest() {
            fx.POBI.addCard(fx.TEN);
            fx.POBI.addCard(fx.TEN);
            Dealer dealer = new Dealer(List.of(fx.ACE, fx.ACE));
            assertThat(PrideCalculator.compute(fx.POBI, dealer))
                    .isEqualTo(10000);
        }
    }

    @Nested
    @DisplayName("딜러 21이하, 플레이어 21이하 결과 무승부 테스트")
    class DealerPlayerUnder3Test {
        private final Fixture fx = new Fixture();

        @Test
        void resultTest() {

            fx.POBI.addCard(fx.ACE);
            fx.POBI.addCard(fx.ACE);
            Dealer dealer = new Dealer(List.of(fx.ACE, fx.ACE));
            assertThat(PrideCalculator.compute(fx.POBI, dealer))
                    .isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("딜러 21이하, 플레이어 버스트 결과 테스트")
    class DealerUnderPlayerHighTest {
        private final Fixture fx = new Fixture();

        @Test
        void resultTest() {
            fx.POBI.addCard(fx.TWO);
            fx.POBI.addCard(fx.TEN);
            fx.POBI.addCard(fx.TEN);
            Dealer dealer = new Dealer(List.of(fx.ACE, fx.NINE));
            assertThat(PrideCalculator.compute(fx.POBI, dealer))
                    .isEqualTo(-10000);
        }
    }
}
