package blackjack.domain.state;

import static blackjack.fixture.CardBundleGenerator.getCardBundleOfBlackjack;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfNonBlackjackTwentyOne;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfSeventeen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfSixteen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTwenty;
import static blackjack.fixture.CardBundleGenerator.getThreeCardsOfNine;
import static blackjack.fixture.CardRepository.CLOVER4;
import static blackjack.fixture.CardRepository.CLOVER5;
import static blackjack.fixture.CardRepository.CLOVER7;
import static blackjack.fixture.CardRepository.CLOVER8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.CardBundle;
import blackjack.domain.card.Score;
import blackjack.strategy.StayStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CanHitTest {

    private final CardBundle threeCards9 = getThreeCardsOfNine();
    private final CardBundle cards10 = getCardBundleOfTen();
    private final CardBundle cards16 = getCardBundleOfSixteen();
    private final CardBundle cards17 = getCardBundleOfSeventeen();
    private final CardBundle cards20 = getCardBundleOfTwenty();
    private final CardBundle threeCards21 = getCardBundleOfNonBlackjackTwentyOne();
    private final CardBundle blackjackCards = getCardBundleOfBlackjack();

    // TODO: 간략하게만 테스트하고, 플레이어와 딜러에서 직접 경우별 테스트
    private final StayStrategy prodPlayerStrategy = (cardBundle) -> cardBundle.getScoreInt() == Score.BLACKJACK;
    private final StayStrategy prodDealerStrategy =
            (cardBundle) -> cardBundle.getScoreInt() > Score.DEALER_EXTRA_CARD_LIMIT;

    @DisplayName("CanHit 생성자 테스트")
    @Nested
    class ConstructorTest {

        @DisplayName("21점 미만의 카드 패로 CanHit 상태 인스턴스를 생성할 수 있다.")
        @Test
        void init_playerCanHitUnder21() {
            assertThatNoException()
                    .isThrownBy(() -> new CanHit(cards20, prodPlayerStrategy));
        }

        @DisplayName("3장으로 구성된 카드 패로 CanHit 상태 인스턴스를 생성할 수 있다.")
        @Test
        void init_multipleCardsOk() {
            assertThatNoException()
                    .isThrownBy(() -> new CanHit(threeCards9, prodPlayerStrategy));
        }

        @DisplayName("블랙잭인 카드 패로 CanHit 인스턴스를 생성하려는 경우, 예외가 발생한다.")
        @Test
        void init_exceptionOnBlackjack() {
            assertThatThrownBy(() -> new CanHit(blackjackCards, prodPlayerStrategy))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이미 패가 확정된 참여자입니다.");
        }

        @DisplayName("플레이어 CanHit 생성자 테스트")
        @Nested
        class PlayerTest {

            @DisplayName("플레이어는 17점 이하 21점 미만의 카드 패로 CanHit 인스턴스를 생성할 수 있다.")
            @Test
            void init_17Ok() {
                assertThatNoException()
                        .isThrownBy(() -> new CanHit(cards17, prodPlayerStrategy));
            }

            @DisplayName("플레이어는 블랙잭이 아닌 21점짜리 카드 패로 CanHit 인스턴스를 생성하려는 경우, 예외가 발생한다.")
            @Test
            void init_exceptionOn17() {
                assertThatThrownBy(() -> new CanHit(threeCards21, prodPlayerStrategy))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이미 패가 확정된 참여자입니다.");
            }
        }

        @DisplayName("딜러 CanHit 생성자 테스트")
        @Nested
        class DealerTest {

            @DisplayName("딜러는 16점 이하의 카드 패로 CanHit 상태 인스턴스를 생성할 수 있다.")
            @Test
            void init_under17Ok() {
                assertThatNoException()
                        .isThrownBy(() -> new CanHit(cards16, prodDealerStrategy));
            }

            @DisplayName("딜러는 17점 이상의 카드 패로 CanHit 인스턴스를 생성하려는 경우, 예외가 발생한다.")
            @Test
            void init_exceptionOn17() {
                assertThatThrownBy(() -> new CanHit(cards17, prodDealerStrategy))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("이미 패가 확정된 참여자입니다.");
            }
        }
    }

    @DisplayName("CanHit 생성자 테스트")
    @Nested
    class HitTest {

        @DisplayName("hit 메서드 호출 결과, 21 넘기는 경우 Bust 상태 인스턴스를 반환한다.")
        @Test
        void hit_bustOnOver21() {
            CardHand prevState = new CanHit(cards16, prodPlayerStrategy);

            CardHand curState = prevState.hit(CLOVER8);

            assertThat(curState).isInstanceOf(Bust.class);
        }

        @DisplayName("hit 메서드 호출 결과, 플레이어가 21 미만이 되는 경우 CanHit 상태 인스턴스를 반환한다.")
        @Test
        void hit_playerCanHitUnder21() {
            CardHand prevState = new CanHit(cards16, prodPlayerStrategy);

            CardHand curState = prevState.hit(CLOVER4);

            assertThat(curState).isInstanceOf(CanHit.class);
        }

        @DisplayName("hit 메서드 호출 결과, 플레이어가 21이 되는 경우 Stay 상태 인스턴스를 반환한다.")
        @Test
        void hit_playerStayOnOver21() {
            CardHand prevState = new CanHit(cards16, prodPlayerStrategy);

            CardHand curState = prevState.hit(CLOVER5);

            assertThat(curState).isInstanceOf(Stay.class);
        }

        @DisplayName("hit 메서드 호출 결과, 딜러가 16 이하가 되는 경우 CanHit 상태 인스턴스를 반환한다.")
        @Test
        void hit_dealerCanHitOn16() {
            CardHand prevState = new CanHit(threeCards9, prodDealerStrategy);

            CardHand curState = prevState.hit(CLOVER7);

            assertThat(curState).isInstanceOf(CanHit.class);
        }

        @DisplayName("hit 메서드 호출 결과, 딜러가 17이 되는 경우 Stay 상태 인스턴스를 반환한다.")
        @Test
        void hit_dealerStayOnOver17() {
            CardHand prevState = new CanHit(cards10, prodDealerStrategy);

            CardHand curState = prevState.hit(CLOVER7);

            assertThat(curState).isInstanceOf(Stay.class);
        }
    }

    @DisplayName("stay 메서드는 동일한 카드 패를 지닌 Stay 상태 인스턴스를 생성하여 반환한다")
    @Test
    void stay() {
        CardHand prevState = new CanHit(cards10, prodPlayerStrategy);

        CardHand curState = prevState.stay();

        assertThat(curState).isInstanceOf(Stay.class);
        assertThat(prevState.getCardBundle())
                .isEqualTo(curState.getCardBundle());
    }

    @DisplayName("CanHit 인스턴스에서 isFinished 메서드를 호출하면 false를 반환한다.")
    @Test
    void isNotFinished() {
        CardHand canHit = new CanHit(cards10, prodPlayerStrategy);

        boolean actual = canHit.isFinished();

        assertThat(actual).isFalse();
    }

    @DisplayName("CanHit 인스턴스에서 isBlackjack 메서드를 호출하면 false를 반환한다.")
    @Test
    void isNotBlackjack() {
        CardHand canHit = new CanHit(cards10, prodPlayerStrategy);

        boolean actual = canHit.isBlackjack();

        assertThat(actual).isFalse();
    }

    @DisplayName("CanHit 인스턴스에서 isBust 메서드를 호출하면 false를 반환한다.")
    @Test
    void isNotBust() {
        CardHand canHit = new CanHit(cards10, prodPlayerStrategy);

        boolean actual = canHit.isBust();

        assertThat(actual).isFalse();
    }
}
