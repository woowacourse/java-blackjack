package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

// TODO: 테스트 리팩토링 - 단위테스트, 파라미터라이즈드, 픽스처
class CardNumberTest {

    @ParameterizedTest
    @CsvSource({
        "JACK,10",
        "QUEEN,10",
        "KING,10",
    })
    @DisplayName("J, Q, K는 각각 10으로 계산한다")
    void cardNumberTest(CardNumber cardNumber, int expected) {
        assertThat(cardNumber.getNumber()).isEqualTo(expected);
    }
}
