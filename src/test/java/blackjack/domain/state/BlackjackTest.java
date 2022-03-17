package blackjack.domain.state;

import static blackjack.fixture.CardBundleGenerator.getCardBundleOfBlackjack;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfNonBlackjackTwentyOne;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTwenty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.CardBundle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BlackjackTest {

    private final CardBundle cards20 = getCardBundleOfTwenty();
    private final CardBundle threeCards21 = getCardBundleOfNonBlackjackTwentyOne();
    private final CardBundle blackjackCards = getCardBundleOfBlackjack();

    @DisplayName("Blackjack 생성자 테스트")
    @Nested
    class ConstructorTest {

        @DisplayName("블랙잭인 카드 패로 Blackjack 인스턴스를 생성할 수 있다.")
        @Test
        void init() {
            assertThatNoException()
                    .isThrownBy(() -> new Blackjack(blackjackCards));
        }

        @DisplayName("21점 미만의 카드 패로 Blackjack 상태 인스턴스를 생성하려는 경우, 예외가 발생한다.")
        @Test
        void init_exceptionOnUnder21() {
            assertThatThrownBy(() -> new Blackjack(cards20))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 카드 패는 블랙잭이 아닙니다.");
        }

        @DisplayName("3장으로 구성된 21점짜리 카드 패로 Blackjack 상태 인스턴스를 생성하려는 경우, 예외가 발생한다.")
        @Test
        void init_exceptionOnMultipleCards21() {
            assertThatThrownBy(() -> new Blackjack(threeCards21))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("해당 카드 패는 블랙잭이 아닙니다.");
        }
    }

    @DisplayName("Blackjack 인스턴스에서 isFinished 메서드를 호출하면 true를 반환한다.")
    @Test
    void isFinished() {
        CardHand blackjack = new Blackjack(blackjackCards);

        boolean actual = blackjack.isFinished();

        assertThat(actual).isTrue();
    }

    @DisplayName("Blackjack 인스턴스에서 isBlackjack 메서드를 호출하면 true를 반환한다.")
    @Test
    void isBlackjack() {
        CardHand blackjack = new Blackjack(blackjackCards);

        boolean actual = blackjack.isBlackjack();

        assertThat(actual).isTrue();
    }

    @DisplayName("Blackjack 인스턴스에서 isBust 메서드를 호출하면 false를 반환한다.")
    @Test
    void isNotBust() {
        CardHand blackjack = new Blackjack(blackjackCards);

        boolean actual = blackjack.isBust();

        assertThat(actual).isFalse();
    }
}
