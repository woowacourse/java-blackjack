package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.cardelement.Suit;
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
    void 문양_알맞게_들어가는지_검사(Suit expectedSuit, String inputSymbolName) {
        assertThat(expectedSuit.getName()).isEqualTo(inputSymbolName);
    }
}
