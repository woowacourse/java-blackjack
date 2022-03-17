package blackjack.domain.state;

import static blackjack.fixture.CardBundleGenerator.getCardBundleOfBlackjack;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfBust;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfNonBlackjackTwentyOne;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTwenty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.CardBundle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class StayTest {

    private final CardBundle cards20 = getCardBundleOfTwenty();
    private final CardBundle threeCards21 = getCardBundleOfNonBlackjackTwentyOne();
    private final CardBundle blackjackCards = getCardBundleOfBlackjack();
    private final CardBundle bustCards = getCardBundleOfBust();

    @DisplayName("Stay 생성자 테스트")
    @Nested
    class ConstructorTest {

        @DisplayName("21 이하의 카드 패로 Stay 인스턴스를 생성할 수 있다.")
        @Test
        void init() {
            assertThatNoException()
                    .isThrownBy(() -> new Stay(cards20));
        }

        @DisplayName("3장으로 구성된 21점짜리 카드 패로 Stay 상태 인스턴스를 생성할 수 있다.")
        @Test
        void init_multipleCards21Ok() {
            assertThatNoException()
                    .isThrownBy(() -> new Stay(threeCards21));
        }

        @DisplayName("블랙잭으로 Stay 상태 인스턴스를 생성하려는 경우, 예외가 발생한다.")
        @Test
        void init_exceptionOnBlackjack() {
            assertThatThrownBy(() -> new Stay(blackjackCards))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("블랙잭은 Stay 상태가 될 수 없습니다.");
        }

        @DisplayName("21점을 초과하는 카드 패로 Stay 상태 인스턴스를 생성하려는 경우, 예외가 발생한다.")
        @Test
        void init_exceptionOnMultipleCards21() {
            assertThatThrownBy(() -> new Stay(bustCards))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("21점 이하의 카드 패만 Stay 상태가 될 수 있습니다.");
        }
    }

    @DisplayName("Stay 인스턴스에서 isFinished 메서드를 호출하면 true를 반환한다.")
    @Test
    void isFinished() {
        CardHand stay = new Stay(cards20);

        boolean actual = stay.isFinished();

        assertThat(actual).isTrue();
    }

    @DisplayName("Blackjack 인스턴스에서 isBlackjack 메서드를 호출하면 false를 반환한다.")
    @Test
    void isNotBlackjack() {
        CardHand stay = new Stay(cards20);

        boolean actual = stay.isBlackjack();

        assertThat(actual).isFalse();
    }

    @DisplayName("Blackjack 인스턴스에서 isBust 메서드를 호출하면 false를 반환한다.")
    @Test
    void isNotBust() {
        CardHand stay = new Stay(cards20);

        boolean actual = stay.isBust();

        assertThat(actual).isFalse();
    }
}
