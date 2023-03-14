package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class CardTest {
    @DisplayName("카드의 값과 모양으로 카드를 생성한다.")
    @Test
    void createTest() {
        Card card = new Card(Value.ACE, Shape.SPADE);
        List<String> expected = List.of(Value.ACE.getValue(), Shape.SPADE.getShape());

        assertThat(List.of(card.getValue(), card.getShape())).isEqualTo(expected);
    }

    @Nested
    @DisplayName("카드의 값 또는 모양으로 null 값이 들어가면 카드 생성에 실패한다.")
    class NullTest {
        @DisplayName("카드의 값으로 null 값이 들어가면 카드 생성에 실패한다.")
        @ParameterizedTest
        @NullSource
        void createTestWithNullValue(Object value) {
            assertThatThrownBy(() -> new Card((Value) value, Shape.SPADE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(Card.NULL_ERROR_MESSAGE);
        }

        @DisplayName("카드의 모양으로 null 값이 들어가면 카드 생성에 실패한다.")
        @ParameterizedTest
        @NullSource
        void createTestWithNullShape(Object shape) {
            assertThatThrownBy(() -> new Card(Value.ACE, (Shape) shape))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(Card.NULL_ERROR_MESSAGE);
        }
    }
}
