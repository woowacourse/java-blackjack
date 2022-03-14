package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class SymbolsTest {

    @Test
    @DisplayName("enum number size가 13개인지 확인")
    void checkValidateEnumNumberSize() {
        List<Symbols> symbolsValues = Arrays.stream(Symbols.values())
                .collect(Collectors.toList());

        assertThat(symbolsValues.size()).isEqualTo(13);
    }

    @ParameterizedTest
    @DisplayName("ace point 점수가 1인지 확인")
    @CsvSource(value = {"ACE:1", "KING:10"}, delimiter = ':')
    void checkEnumNumberPoint(Symbols value, int point) {
        assertThat(value.getPoint()).isEqualTo(point);
    }
}
