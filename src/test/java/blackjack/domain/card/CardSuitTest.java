package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardSuitTest {
    @ParameterizedTest
    @CsvSource(value = {"HEART,하트", "DIAMOND,다이아몬드", "SPADE,스페이드", "CLOVER,클로버"})
    @DisplayName("getType 정상 작동에 대한 테스트")
    void getTypeTest(final String cardSuit, final String type) {
        assertThat(CardSuit.valueOf(cardSuit).getType()).isEqualTo(type);
    }
}
