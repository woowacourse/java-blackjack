package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    @DisplayName("덱 생성 테스트")
    void 덱_생성_테스트() {
        // when & then
        assertThatCode(Deck::new)
                .doesNotThrowAnyException();
    }
}
