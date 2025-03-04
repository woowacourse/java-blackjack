package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GivenCardsTest {

    @DisplayName("중복이 아닌 카드를 저장할 시 True를 반환한다")
    @Test
    void test2() {
        // given
        Card card = new Card(CardNumberType.SIX, CardType.CLOVER);
        GivenCards givenCards = GivenCards.createEmpty();

        // when
        boolean isUnique = givenCards.addUnique(card);

        // then
        assertThat(isUnique).isTrue();
    }

    @DisplayName("이미 저장된 카드를 저장할 시 False를 반환한다")
    @Test
    void test3() {
        // given
        Card card = new Card(CardNumberType.SIX, CardType.CLOVER);
        GivenCards givenCards = GivenCards.createEmpty();
        givenCards.addUnique(card);

        // when
        boolean isUnique = givenCards.addUnique(card);

        // then
        assertThat(isUnique).isFalse();
    }
}
