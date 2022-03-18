package blackjack.domain.state;

import static blackjack.fixture.CardBundleFixture.BUST_CARD_BUNDLE;
import static blackjack.fixture.CardBundleFixture.CARD_BUNDLE_21;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.CardBundle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BustTest {

    private final CardBundle threeCards21 = CARD_BUNDLE_21();
    private final CardBundle bustCards = BUST_CARD_BUNDLE();

    @DisplayName("21점을 초과하는 카드 패로 Bust 인스턴스를 생성할 수 있다.")
    @Test
    void init() {
        assertThatNoException()
                .isThrownBy(() -> new Bust(bustCards));
    }

    @DisplayName("21점 이하의 카드 패로 Bust 상태 인스턴스를 생성하려는 경우, 예외가 발생한다.")
    @Test
    void init_exceptionOnUnder21() {
        assertThatThrownBy(() -> new Bust(threeCards21))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("21점을 초과하는 카드 패만 Bust 상태가 될 수 있습니다.");
    }

    @DisplayName("Bust 인스턴스에서 isFinished 메서드를 호출하면 true를 반환한다.")
    @Test
    void isFinished() {
        CardHand bust = new Bust(bustCards);

        boolean actual = bust.isFinished();

        assertThat(actual).isTrue();
    }

    @DisplayName("Bust 인스턴스에서 isBlackjack 메서드를 호출하면 false를 반환한다.")
    @Test
    void isNotBlackjack() {
        CardHand bust = new Bust(bustCards);

        boolean actual = bust.isBlackjack();

        assertThat(actual).isFalse();
    }

    @DisplayName("Bust 인스턴스에서 isBust 메서드를 호출하면 true를 반환한다.")
    @Test
    void isBust() {
        CardHand bust = new Bust(bustCards);

        boolean actual = bust.isBust();

        assertThat(actual).isTrue();
    }
}

