package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardTest {

    @DisplayName("보장된(트럼프의 카드문양, 카드숫자가 맞음) 카드가 들어올 시 정상 생성된다.")
    @Test
    void card_generate_test() {
        assertThatCode(() -> Card.of("스페이드", "A"))
                .doesNotThrowAnyException();
        assertThatCode(() -> Card.of("하트", "J"))
                .doesNotThrowAnyException();
        assertThatCode(() -> Card.of("다이아몬드", "Q"))
                .doesNotThrowAnyException();
        assertThatCode(() -> Card.of("클로버", "K"))
                .doesNotThrowAnyException();
    }

    @DisplayName("잘못된 카드 숫자가 들어오면, 에러가 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"스페이드:11", "하트:-1"}, delimiter = ':')
    void card_wrong_number_generate_error_test(String symbolName, String numberName) {
        assertThatThrownBy(() -> Card.of(symbolName, numberName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("입력된 숫자는 없는 카드 숫자입니다! : %s", numberName));
    }

    @DisplayName("잘못된 카드 문양 들어오면, 에러가 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"수페이드:A", "할트:J", "달리아몬드:10"}, delimiter = ':')
    void card_wrong_symbol_generate_error_test(String symbolName, String numberName) {
        assertThatThrownBy(() -> Card.of(symbolName, numberName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("입력된 문양은 없는 카드문양입니다! : %s", symbolName));
    }
}
