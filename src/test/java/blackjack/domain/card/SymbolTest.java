package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SymbolTest {
    private static Stream<Arguments> symbolWithName() {
        return Stream.of(
                Arguments.of("스페이드", Symbol.SPADE),
                Arguments.of("하트", Symbol.HEART),
                Arguments.of("클로버", Symbol.CLOVER),
                Arguments.of("다이아몬드", Symbol.DIAMOND)
        );
    }

    @DisplayName("카드문양(스페이드, 하트, 클로버, 다이아몬드)을 통해 Symbol 가져온다.")
    @ParameterizedTest
    @MethodSource("symbolWithName")
    void symbol_from_test(String name, Symbol symbol) {
        Symbol findSymbol = Symbol.from(name);

        assertThat(findSymbol).isEqualTo(symbol);
    }

    @DisplayName("카드문양(스페이드, 하트, 클로버, 다이아몬드)가 아니면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"스페이스, 하드, 글로버, 타이아몬드"})
    void symbol_wrong_name_exception_test(String name) {
        assertThatThrownBy(() -> Symbol.from(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
