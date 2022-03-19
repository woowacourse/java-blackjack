package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.card.denomination.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SuitTest {

    @ParameterizedTest
    @CsvSource(value = {
            "SPADE,스페이드",
            "HEART,하트",
            "DIAMOND,다이아몬드",
            "CLOVER,클로버"
    })

    @DisplayName("올바른 문양 들어가는지 테스트")
    void setExpectedSuitTest(Suit expectedSuit, String inputSymbolName) {
        assertThat(expectedSuit.get())
                .isEqualTo(inputSymbolName);
    }
}
