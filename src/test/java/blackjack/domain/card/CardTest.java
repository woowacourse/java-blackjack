package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    @DisplayName("동일 suit, denomination을 가진 카드는 캐싱된 카드에서 가져오는 동일 객체인지 테스트")
    void testOf() {
        //when
        Card card = Card.of(Suit.CLUB, Denomination.TEN);

        //then
        assertThat(card).isSameAs(Card.of(Suit.CLUB, Denomination.TEN));
    }

    @Test
    @DisplayName("denomination 반환 테스트")
    void testDenomination() {
        //given
        Card card = Card.of(Suit.CLUB, Denomination.TEN);

        //when
        String denomination = card.denomination();

        //then
        assertThat(denomination).isSameAs(Denomination.TEN.getDenomination());
    }

}
