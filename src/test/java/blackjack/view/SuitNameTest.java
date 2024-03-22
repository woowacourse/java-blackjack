package blackjack.view;

import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("카드 문양 이름")
class SuitNameTest {
    @Test
    @DisplayName("모두 변환이 된다.")
    void convertAllTest() {
        // given & when & then
        assertThatCode(() ->
                Arrays.stream(Suit.values())
                        .forEach(SuitName::convert)
        )
                .doesNotThrowAnyException();
    }
}
