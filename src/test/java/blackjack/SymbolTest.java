package blackjack;

import blackjack.domain.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SymbolTest {
    @DisplayName("심볼의 한글 이름을 반환하는지 테스트")
    @ParameterizedTest
    @CsvSource({"HEART,하트", "DIAMOND,다이아몬드", "CLOVER,클로버", "SPADE,스페이드"})
    void getKoreanNameTest(Symbol symbol, String expected) {
        assertThat(symbol.getKoreanName()).isEqualTo(expected);
    }
}