package blackjack.view;

import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("카드 문양 이름")
class CardSuitNameTest {
    @Test
    @DisplayName("이 모두 변환 된다.")
    void convertAllTest() {
        // given & when & then
        assertThatCode(() ->
                Arrays.stream(CardSuit.values())
                        .forEach(CardSuitName::convert)
        )
                .doesNotThrowAnyException();
    }
}
