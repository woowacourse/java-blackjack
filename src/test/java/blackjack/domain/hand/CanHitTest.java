package blackjack.domain.hand;

import static blackjack.fixture.CardBundleFixture.CARD_BUNDLE_21;
import static blackjack.fixture.CardBundleFixture.CARD_BUNDLE_10;
import static blackjack.fixture.CardBundleFixture.CARD_BUNDLE_20;
import static blackjack.fixture.CardRepository.CLOVER2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import blackjack.domain.card.CardBundle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CanHitTest {

    private static final CardBundle CARD_BUNDLE_10 = CARD_BUNDLE_10();
    private static final CardBundle CARD_BUNDLE_20 = CARD_BUNDLE_20();
    private static final CardBundle CARD_BUNDLE_21 = CARD_BUNDLE_21();

    @DisplayName("21짜리 패로 구성된 CanHit 인스턴스도 존재할 수 있다. (즉시 stay 호출 필요)")
    @Test
    void init_21() {
        assertThatNoException()
                .isThrownBy(() -> new CanHit(CARD_BUNDLE_21));
    }

    @DisplayName("hit 메서드 실행시, 21을 초과하지 않으면 CanHit 인스턴스를 반환한다.")
    @Test
    void hit_canHitAgainOnUnder21() {
        CardHand cardHand = new CanHit(CARD_BUNDLE_10).hit(CLOVER2);

        assertThat(cardHand).isInstanceOf(CanHit.class);
    }

    @DisplayName("hit 메서드 실행시, 21을 초과하게 되면 Bust 인스턴스를 반환한다.")
    @Test
    void hit_bust() {
        CardHand cardHand = new CanHit(CARD_BUNDLE_20)
                .hit(CLOVER2);

        assertThat(cardHand).isInstanceOf(Bust.class);
    }

    @DisplayName("stay 메서드 실행시 Stay 인스턴스를 반환한다.")
    @Test
    void stay() {
        CardHand cardHand = new CanHit(CARD_BUNDLE_20).stay();

        assertThat(cardHand).isInstanceOf(Stay.class);
    }

    @DisplayName("isFinished 메서드 실행시 false가 반환된다.")
    @Test
    void isFinished() {
        boolean isFinished = new CanHit(CARD_BUNDLE_20).isFinished();

        assertThat(isFinished).isFalse();
    }
}
