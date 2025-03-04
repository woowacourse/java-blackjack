package domain;

import static org.assertj.core.api.Assertions.assertThat;

import fixture.CardFixture;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardHandTest {
    @Test
    @DisplayName("카드를 추가한다.")
    void testAddCard() {
        // given
        CardHand cardHand = new CardHand(Set.of(CardFixture.of(1)));
        // when
        cardHand.add(CardFixture.of(0));
        // then
        CardHand expected = new CardHand(Set.of(CardFixture.of(0), CardFixture.of(1)));
        assertThat(cardHand).isEqualTo(expected);
    }
}
