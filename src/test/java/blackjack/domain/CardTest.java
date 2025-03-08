package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Nested
    @DisplayName("카드 생성 테스트")
    class GenerateCardsTest {

        @Test
        @DisplayName("카드가 52장이 만들어진다.")
        void generateCardCache() {
            assertThat(Card.values()).hasSize(52);
        }
    }
}
