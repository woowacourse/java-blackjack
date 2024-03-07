package blackjack.view;

import blackjack.domain.card.CardScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("카드 점수 이름")
class CardScoreNameTest {
    @Test
    @DisplayName("모두 변환이 된다.")
    void convertAllTest() {
        // given & when & then
        assertThatCode(() ->
                Arrays.stream(CardScore.values())
                        .forEach(CardScoreName::convert)
        )
                .doesNotThrowAnyException();
    }
}
