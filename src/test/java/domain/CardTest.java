package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardType;
import domain.card.CardValue;
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
        CardType[] types = CardType.values();
        Card card = new Card(types[index], CardValue.ACE);

        // when
        boolean result = card.isAce();

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("카드가 에이스라면 false를 반환한다.")
    @Test
    void return_false_when_is_not_ace() {
        // given
        Card card = new Card(CardType.CLUB, CardValue.FIVE);

        // when
        boolean result = card.isAce();

        // then
        assertThat(result).isFalse();
    }
}
