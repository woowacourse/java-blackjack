package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @DisplayName("Card 객체를 생성한다")
    @Test
    void testInit() {
        //given
        CardType cardType = CardType.CLOVER;
        CardValue cardValue = CardValue.ACE;

        //when
        Card card = new Card(cardType, cardValue);

        //then
        assertThat(card).isNotNull();
    }

    @DisplayName("이 카드가 ACE 카드인지 확인한다")
    @Test
    void testIsAce() {
        //given
        CardType cardType = CardType.CLOVER;
        CardValue cardValue = CardValue.ACE;
        Card card = new Card(cardType, cardValue);

        //when
        boolean actual = card.isAce();

        //then
        assertThat(actual).isTrue();
    }
}
