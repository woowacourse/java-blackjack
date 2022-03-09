package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("카드 객체 생성을 테스트한다.")
    @Test
    public void testCreateCard() {
        //given
        Suit spade = Suit.SPADE;
        Denomination ace = Denomination.ACE;

        //when
        Card card = new Card(spade, ace);

        //then
        assertThat(card).isNotNull();
    }

    @DisplayName("카드의 이름을 얻을 수 있다.")
    @Test
    public void testGetCardName() {
        //given
        Suit suit = Suit.SPADE;
        Denomination denomination = Denomination.EIGHT;

        //when
        Card card = new Card(suit, denomination);

        //then
        assertThat(card.getName()).isEqualTo("8스페이드");

    }
}
