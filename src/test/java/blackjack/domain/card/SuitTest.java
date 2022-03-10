package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
class SuitTest {
    @ParameterizedTest
    @CsvSource(value = {
            "SPADE,스페이드",
            "HEART,하트",
            "DIAMOND,다이아몬드",
            "CLOVER,클로버"
    })
    void 테스트(Suit expectedSuit, String inputSymbolName) {
        assertThat(expectedSuit.getName())
                .isEqualTo(inputSymbolName);
    }
}