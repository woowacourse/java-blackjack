package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SuitTest {

    @Test
    @DisplayName("카드 무늬는 4종류이다")
    void suitCount() {
        assertThat(Suit.values()).hasSize(4);
    }

    @ParameterizedTest
    @DisplayName("각 무늬는 정해진 한글 이름을 가진다")
    @CsvSource({
            "HEART,   하트",
            "DIAMOND, 다이아몬드",
            "SPADE,   스페이드",
            "CLUB,    클로버"
    })
    void suitDisplayName(Suit suit, String expected) {
        assertThat(suit.getDisplayName()).isEqualTo(expected);
    }
}
