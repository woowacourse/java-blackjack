package blackjack.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ConverterTest {

    @Test
    void 콤마로_구분된_문자열을_리스트로_변환할_수_있다() {
        final List<String> result = Converter.stringToList("포비, 뽀로로, 수달");

        assertThat(result).containsExactly("포비", "뽀로로", "수달");
    }
}
