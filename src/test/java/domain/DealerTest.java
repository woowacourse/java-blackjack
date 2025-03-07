package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {


    @Nested
    class InvalidCases {

        @DisplayName("딜러는 손패를 가져야 한다.")
        @Test
        void validateNotNull() {
            // given
            Hand nullHand = null;

            // when & then
            assertThatThrownBy(() -> new Dealer(nullHand))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("딜러는 손패를 가져야합니다.");
        }

}
