package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KindTest {

    @DisplayName("카드 문양 테스트")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @CsvSource(value = {"SPADE, 스페이드", "DIAMOND, 다이아몬드", "CLOVER, 클로버", "HEART, 하트"})
    void getSymbol_AllKinds_ReturnExactSymbol(Kind kind, String symbol) {
        assertThat(kind.getSymbol()).isEqualTo(symbol);
    }
}
