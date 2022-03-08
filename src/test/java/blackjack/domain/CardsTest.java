package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("여러장의 카드가 주어지면 객체를 생성한다.")
    void createCards() {
        // given
        final Set<Card> rawCards = Set.of(new Card(CardSymbol.DIAMOND, CardNumber.ACE),
                new Card(CardSymbol.DIAMOND, CardNumber.TEN));
        final Cards cards = new Cards(rawCards);

        // when
        int actual = cards.getSize();

        // then
        assertThat(actual).isEqualTo(2);
    }
}