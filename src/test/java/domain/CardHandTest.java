package domain;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CardHandTest {
    @Test
    void 카드를_한_장_받는다() {
        CardHand cardHand = new CardHand();
        cardHand.addCard(카드());

        assertThat(cardHand.getCards()).hasSize(1);
    }

    @Test
    void 카드의_합을_계산한다() {
        CardHand cardHand = new CardHand();
        cardHand.addCard(카드(Denomination.TEN));
        cardHand.addCard(카드(Denomination.SIX));

        int result = cardHand.sumAll();

        assertThat(result).isEqualTo(16);
    }

    @Test
    void Ace는_1_또는_11로_계산할_수_있다_1() {
        CardHand cardHand = new CardHand();
        cardHand.addCard(카드(Denomination.ACE));
        cardHand.addCard(카드(Denomination.SIX));

        int result = cardHand.sumAll();

        assertThat(result).isEqualTo(17);
    }

    @Test
    void Ace는_1_또는_11로_계산할_수_있다_2() {
        CardHand cardHand = new CardHand();
        cardHand.addCard(카드(Denomination.ACE));
        cardHand.addCard(카드(Denomination.ACE));

        int result = cardHand.sumAll();

        assertThat(result).isEqualTo(12);
    }

    @Test
    void Ace는_1_또는_11로_계산할_수_있다_3() {
        CardHand cardHand = new CardHand();
        cardHand.addCard(카드(Denomination.ACE));
        cardHand.addCard(카드(Denomination.KING));
        cardHand.addCard(카드(Denomination.JACK));

        int result = cardHand.sumAll();

        assertThat(result).isEqualTo(21);
    }
}
