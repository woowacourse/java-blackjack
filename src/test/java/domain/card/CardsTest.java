package domain.card;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CardsTest {
    @Test
    void 카드를_한_장_받는다() {
        Cards cards = new Cards();
        cards.addCard(카드());

        assertThat(cards.getCards()).hasSize(1);
    }

    @Test
    void 카드의_합을_계산한다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.TEN));
        cards.addCard(카드(Denomination.SIX));

        int result = cards.sumAll();

        assertThat(result).isEqualTo(16);
    }

    @Test
    void Ace는_1_또는_11로_계산할_수_있다_1() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.ACE));
        cards.addCard(카드(Denomination.SIX));

        int result = cards.sumAll();

        assertThat(result).isEqualTo(17);
    }

    @Test
    void Ace는_1_또는_11로_계산할_수_있다_2() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.ACE));
        cards.addCard(카드(Denomination.ACE));

        int result = cards.sumAll();

        assertThat(result).isEqualTo(12);
    }

    @Test
    void Ace는_1_또는_11로_계산할_수_있다_3() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.ACE));
        cards.addCard(카드(Denomination.KING));
        cards.addCard(카드(Denomination.JACK));

        int result = cards.sumAll();

        assertThat(result).isEqualTo(21);
    }
}
