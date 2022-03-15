package blackjack.domain.card;

import static blackjack.fixture.CardBundleGenerator.getCardBundleOfBlackjack;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfFifteen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfNonBlackjackTwentyOne;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTwenty;
import static blackjack.fixture.CardRepository.CLOVER2;
import static blackjack.fixture.CardRepository.CLOVER3;
import static blackjack.fixture.CardRepository.CLOVER5;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import blackjack.domain.game.Score;
import blackjack.strategy.CardHandStateStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CardHandTest {

    private final CardHandStateStrategy playerStateStrategy = CardHandState::ofPlayer;
    private final CardHandStateStrategy dealerStateStrategy = CardHandState::ofDealer;

    @DisplayName("최초로 인스턴스 생성시, 자동으로 CAN_HIT 혹은 BLACKJACK 중 하나의 값으로 state가 초기화된다.")
    @Nested
    class OfInitialTest {

        @DisplayName("플레이어 상태 전략 사용시, 초기 패가 21 미만인 경우 isFinished 메서드 호출시 false가 반환된다.")
        @Test
        void initWithPlayerStrategy_canHitStateOnUnder21() {
            CardHand cardHand = CardHand.of(getCardBundleOfTwenty(), playerStateStrategy);

            boolean actual = cardHand.isFinished();

            assertThat(actual).isFalse();
        }

        @DisplayName("딜러 상태 전략 사용시, 초기 패가 17 미만인 경우 isFinished 메서드 호출시 false가 반환된다.")
        @Test
        void initWithDealerStrategy_canHitStateOnUnder17() {
            CardHand cardHand = CardHand.of(getCardBundleOfFifteen(), dealerStateStrategy);

            boolean actual = cardHand.isFinished();

            assertThat(actual).isFalse();
        }

        @DisplayName("어떤 전략이든 초기 패가 블랙잭인 경우 isBlackjack 메서드 호출시 true가 반환된다.")
        @Test
        void init_blackjackOnBlackjack() {
            CardHand playerCardHand = CardHand.of(getCardBundleOfBlackjack(), playerStateStrategy);
            CardHand dealerCardHand = CardHand.of(getCardBundleOfBlackjack(), dealerStateStrategy);

            boolean playerActual = playerCardHand.isBlackjack();
            boolean dealerActual = dealerCardHand.isBlackjack();

            assertThat(playerActual).isTrue();
            assertThat(dealerActual).isTrue();
        }
    }

    @DisplayName("hit 메서드 호출시 패에 카드 한 장을 추가하며, 자동으로 상태가 업데이트된다.")
    @Nested
    class HitInitialTest {

        @DisplayName("플레이어 상태 전략 사용시, 21 미만의 패를 지닌 경우 hit 메서드를 호출할 수 있다.")
        @Test
        void hitWithPlayerStrategy_canHitUnder21_becomesBust() {
            CardHand cardHand = CardHand.of(getCardBundleOfTwenty(), playerStateStrategy);

            cardHand.hit(CLOVER2, playerStateStrategy);

            assertThat(cardHand.getScore()).isEqualTo(Score.valueOf(22));
            assertThat(cardHand.isFinished()).isTrue();
            assertThat(cardHand.isBust()).isTrue();
        }

        @DisplayName("플레이어 상태 전략 사용시, 21의 패를 지녔을 때 hit 메서드를 호출하려는 경우 예외가 발생한다.")
        @Test
        void hit_throwsExceptionOn21() {
            CardHand cardHand = CardHand.of(getCardBundleOfNonBlackjackTwentyOne(), playerStateStrategy);

            assertThat(cardHand.isFinished()).isTrue();
            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> cardHand.hit(CLOVER2, playerStateStrategy))
                    .withMessage("이미 카드 패가 확정된 참여자입니다.");
        }

        @DisplayName("21의 패를 지닌 경우, hit 메서드를 호출하려는 경우 예외가 발생한다.")
        @Test
        void hit_throwsExceptionOnBlackjack() {
            CardHand cardHand = CardHand.of(getCardBundleOfBlackjack(), playerStateStrategy);

            assertThat(cardHand.isFinished()).isTrue();
            assertThat(cardHand.isBlackjack()).isTrue();
            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> cardHand.hit(CLOVER2, playerStateStrategy))
                    .withMessage("이미 카드 패가 확정된 참여자입니다.");
        }

        @DisplayName("플레이어 상태 전략 사용시, 패가 21 미만인 동안에는 hit 메서드를 반복적으로 호출할 수 있다.")
        @Test
        void hitWithPlayerStrategy_canHitAgainUnder21() {
            CardHand cardHand = CardHand.of(getCardBundleOfTen(), playerStateStrategy);

            cardHand.hit(CLOVER2, playerStateStrategy);
            cardHand.hit(CLOVER3, playerStateStrategy);
            cardHand.hit(CLOVER5, playerStateStrategy);

            assertThat(cardHand.getScore()).isEqualTo(Score.valueOf(20));
            assertThat(cardHand.isFinished()).isFalse();
        }

        @DisplayName("딜러 상태 전략 사용시, 패가 17 미만인 동안에는 hit 메서드를 반복적으로 호출할 수 있다.")
        @Test
        void hitWithDealerStrategy_canHitAgainUnder21() {
            CardHand cardHand = CardHand.of(getCardBundleOfTen(), dealerStateStrategy);

            cardHand.hit(CLOVER2, playerStateStrategy);
            cardHand.hit(CLOVER3, playerStateStrategy);

            assertThat(cardHand.getScore()).isEqualTo(Score.valueOf(15));
            assertThat(cardHand.isFinished()).isFalse();
        }
    }

    @DisplayName("stay 메서드 호출시 패가 21 미만이어도 더 이상 hit 메서드를 호출할 수 없게 된다.")
    @Nested
    class StayInitialTest {

        @DisplayName("21 미만의 패를 지닌 경우, stay 메서드를 호출할 수 있다.")
        @Test
        void stay_canStayUnder21_becomesStay() {
            CardHand cardHand = CardHand.of(getCardBundleOfTwenty(), playerStateStrategy);

            cardHand.stay();

            assertThat(cardHand.getScore()).isEqualTo(Score.valueOf(20));
            assertThat(cardHand.isFinished()).isTrue();
            assertThat(cardHand.isBlackjack()).isFalse();
            assertThat(cardHand.isBust()).isFalse();
        }

        @DisplayName("딜러 상태 전략 사용시 17 이상 21 이하의 패를 지닌 경우 stay 메서드를 호출하지 않아도 자동으로 STAY 상태가 된다.")
        @Test
        void automaticallyBecomesStayOnOver16WithDealerStrategy() {
            CardHand cardHand = CardHand.of(getCardBundleOfTwenty(), dealerStateStrategy);

            assertThat(cardHand.getScore()).isEqualTo(Score.valueOf(20));
            assertThat(cardHand.isFinished()).isTrue();
            assertThat(cardHand.isBlackjack()).isFalse();
            assertThat(cardHand.isBust()).isFalse();
        }

        @DisplayName("이미 21 이상의 패를 지닌 상태에서 stay 메서드를 호출하려는 경우 예외가 발생한다.")
        @Test
        void stay_throwsExceptionOnAlreadyFinished21() {
            CardHand cardHand = CardHand.of(getCardBundleOfNonBlackjackTwentyOne(), playerStateStrategy);

            assertThat(cardHand.isFinished()).isTrue();
            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(cardHand::stay)
                    .withMessage("이미 카드 패가 확정된 참여자입니다.");
        }

        @DisplayName("21 미만의 패를 지니더라도 stay 메서드를 여러 번 호출하는 경우 예외가 발생한다.")
        @Test
        void hit_throwsExceptionOnAlreadyStayState() {
            CardHand cardHand = CardHand.of(getCardBundleOfTwenty(), playerStateStrategy);

            cardHand.stay();

            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(cardHand::stay)
                    .withMessage("이미 카드 패가 확정된 참여자입니다.");
        }
    }
}
