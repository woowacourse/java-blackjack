package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GivenCardsTest {

    @DisplayName("카드를 저장한다")
    @Test
    void test2() {
        // given
        Card card = new Card(CardNumberType.SIX, CardType.CLOVER);
        GivenCards givenCards = GivenCards.createEmpty();

        // when
        givenCards.add(card);

        // then
        assertThat(givenCards.contains(card)).isTrue();
    }
}
