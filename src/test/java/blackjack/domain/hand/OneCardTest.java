package blackjack.domain.hand;

import static blackjack.fixture.CardRepository.CLOVER10;
import static blackjack.fixture.CardRepository.CLOVER2;
import static blackjack.fixture.CardRepository.CLOVER_ACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OneCardTest {

    @DisplayName("한 장의 카드로 OneCard 인스턴스를 생성할 수 있다.")
    @Test
    void init() {
        assertThatNoException()
                .isThrownBy(() -> new OneCard(CLOVER2));
    }

    @DisplayName("hit 메서드 실행시, 블랙잭이 되면 Blackjack 인스턴스를 반환한다.")
    @Test
    void hit_blackjack() {
        CardHand cardHand = new OneCard(CLOVER10).hit(CLOVER_ACE);

        assertThat(cardHand).isInstanceOf(Blackjack.class);
    }

    @DisplayName("hit 메서드 실행시, 블랙잭이 되지 않으면 CanHit 인스턴스를 반환한다.")
    @Test
    void hit_canHitOnTwoCardsNonBlackjack() {
        CardHand cardHand = new OneCard(CLOVER10).hit(CLOVER2);

        assertThat(cardHand).isInstanceOf(CanHit.class);
    }

    @DisplayName("stay 메서드 실행시 예외가 발생한다.")
    @Test
    void stay() {
        CardHand cardHand = new OneCard(CLOVER10);

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(cardHand::stay);
    }

    @DisplayName("isFinished 메서드 실행시 false가 반환된다.")
    @Test
    void isFinished() {
        boolean isFinished = new OneCard(CLOVER10).isFinished();

        assertThat(isFinished).isFalse();
    }
}
