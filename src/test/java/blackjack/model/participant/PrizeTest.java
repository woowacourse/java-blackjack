package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PrizeTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 101, 1020})
    void prize(int amount) {
        assertThatThrownBy(() -> Prize.of(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("100원 단위의 자연수가 아닙니다.");
    }

    @DisplayName("원래 최종 우승 상금에 -1을 곱해서 반환한다.")
    @Test
    void lose() {
        //given
        Prize prize = Prize.of(100);

        //when & then
        assertThat(prize.lose().getAmount()).isEqualTo(-100);
    }

    @DisplayName("원래 최종 우승 상금이 음수이면 예외가 발생한다.")
    @Test
    void lose_of_negative_number() {
        //given
        Prize prize = Prize.of(100);
        Prize lost = prize.lose();

        //when & then
        assertThatThrownBy(lost::lose)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 패배 처리되었습니다.");
    }

    @DisplayName("isBlackjack이 true이면, 예외가 발생한다.")
    @Test
    void lose_of_blackjack() {
        //given
        Prize prize = Prize.of(100);
        Prize blackjack = prize.blackjack();

        //when & then
        assertThatThrownBy(blackjack::lose)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 블랙잭 처리됐습니다.");
    }

    @DisplayName("원래 최종 우습 상금에 1.5를 곱해서 반환한다.")
    @Test
    void blackjack() {
        //given
        Prize prize = Prize.of(100);

        //when & then
        assertThat(prize.blackjack().getAmount()).isEqualTo(150);
    }

    @DisplayName("원래 최종 우습 상금이 음수이면 예외가 발생한다.")
    @Test
    void blackjack_of_negative_number() {
        //given
        Prize prize = Prize.of(100);
        Prize lost = prize.lose();

        //when & then
        assertThatThrownBy(lost::blackjack)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 패배 처리되었습니다.");
    }

    @DisplayName("isBlackjack이 true이면, 예외가 발생한다.")
    @Test
    void blackjack_of_blackjack() {
        //given
        Prize prize = Prize.of(100);
        Prize blackjack = prize.blackjack();

        //when & then
        assertThatThrownBy(blackjack::blackjack)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 블랙잭 처리됐습니다.");
    }
}