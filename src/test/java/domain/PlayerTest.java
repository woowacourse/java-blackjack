package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest {

    @Nested
    class InvalidCase {

        @ParameterizedTest
        @CsvSource(value = {
                "null",
                "' '"},
                nullValues = {"null"})
        @DisplayName("플레이어는 이름을 가져야 한다.")
        void validateNotNullName(String name) {
            // given
            Hand hand = new Hand(List.of(TrumpCard.ACE_OF_SPADES, TrumpCard.TEN_OF_SPADES));

            // when & then
            assertThatThrownBy(() -> new Player(name, hand))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어는 이름을 가져야합니다.");
        }

        @Test
        @DisplayName("플레이어는 손패를 가져야 한다.")
        void validateNotNullHand() {
            // given
            Hand nullHand = null;

            // when & then
            assertThatThrownBy(() -> new Player("머피", nullHand))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("참가자는 손패를 가져야합니다.");
        }
    }
}
