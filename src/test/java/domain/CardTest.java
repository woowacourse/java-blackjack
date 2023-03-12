package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Type;
import domain.card.Value;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CardTest {

    @DisplayName("카드가 에이스라면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void return_true_when_is_ace(int index) {
        // given
        Type[] types = Type.values();
        Card card = new Card(types[index], Value.ACE);

        // when
        boolean result = card.isAce();

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("카드가 에이스라면 false를 반환한다.")
    @Test
    void return_false_when_is_not_ace() {
        // given
        Card card = new Card(Type.CLUB, Value.FIVE);

        // when
        boolean result = card.isAce();

        // then
        assertThat(result).isFalse();
    }
}
