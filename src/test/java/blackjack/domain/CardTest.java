package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Nested
public class CardTest {

    @Nested
    @DisplayName("카드 생성 테스트")
    class GenerateCards {

        @Test
        @DisplayName("카드가 52장이 만들어진다.")
        void generateCardCache() {
            assertThat(Card.values().size()).isEqualTo(52);
        }
    }
}
