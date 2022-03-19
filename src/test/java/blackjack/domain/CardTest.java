package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.domain.card.Card;
import blackjack.domain.card.pattern.Denomination;
import blackjack.domain.card.pattern.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드 생성 확인")
    void createCard() {
        Suit pattern = Suit.HEART;
        Denomination denomination = Denomination.ACE;

        Card card = new Card(pattern, denomination);

        assertThat(card).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드의 필드값이 같을 때 객체 동등성 비교가 성공한다.")
    void equalsAndHashCode() {
        Suit pattern = Suit.SPADE;
        Denomination denomination = Denomination.TEN;

        assertThat(new Card(pattern, denomination)).isEqualTo(new Card(pattern, denomination));
    }

    @Test
    @DisplayName("카드의 Denomination 값이 ACE 이다.")
    void isCardDenominationAce() {
        Card card = new Card(Suit.DIAMOND, Denomination.ACE);
        assertTrue(card.isAce());
    }

    @Test
    @DisplayName("카드의 Denomination Point를 확인한다.")
    void checkCardDenominationPoint() {
        Card card = new Card(Suit.DIAMOND, Denomination.JACK);
        assertThat(card.denominationPoint()).isEqualTo(10);
    }

    @Test
    @DisplayName("카드의 Denomination name과 Suit name을 확인한다.")
    void checkCardOfDenominationAndSuit() {
        Card card = new Card(Suit.DIAMOND, Denomination.JACK);
        assertThat(card.getName()).isEqualTo("J다이아몬드");
    }

}
