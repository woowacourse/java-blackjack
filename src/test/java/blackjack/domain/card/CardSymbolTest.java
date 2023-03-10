package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardSymbolTest {

    @ParameterizedTest(name = "input : {0}")
    @CsvSource(value = {"하트:HEART", "다이아몬드:DIAMOND", "스페이드:SPADE", "클로버:CLOVER"}, delimiter = ':')
    @DisplayName("of()는 심볼 이름를 받아 해당 enum 을 반환한다.")
    void test_(String symbolName, String symbol) {
        // given & when
        CardSymbol expected = CardSymbol.valueOf(symbol);

        // then
        Assertions.assertThat(CardSymbol.of(symbolName)).isEqualTo(expected);
    }

}
