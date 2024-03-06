package domain;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardsTest {
    private Cards cards;

    @BeforeEach
    void setUp() {
        cards = new Cards();
    }

    @Test
    void 카드를_받을_수_있다() {
        cards.addCard(카드());

        assertThat(cards).extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(1);
    }

    @Test
    void 카드의_합을_계산한다() {
        cards.addCard(카드(Denomination.TEN));
        cards.addCard(카드(Denomination.SIX));

        int result = cards.sumAll();

        assertThat(result).isEqualTo(16);
    }

    @Test
    void Ace는_1_또는_11로_계산할_수_있다_1() {
        cards.addCard(카드(Denomination.ACE));
        cards.addCard(카드(Denomination.SIX));

        int result = cards.sumAll();

        assertThat(result).isEqualTo(17);
    }

    @Test
    void Ace는_1_또는_11로_계산할_수_있다_2() {
        cards.addCard(카드(Denomination.ACE));
        cards.addCard(카드(Denomination.ACE));

        int result = cards.sumAll();

        assertThat(result).isEqualTo(12);
    }

    @Test
    void Ace는_1_또는_11로_계산할_수_있다_3() {
        cards.addCard(카드(Denomination.ACE));
        cards.addCard(카드(Denomination.KING));
        cards.addCard(카드(Denomination.JACK));

        int result = cards.sumAll();

        assertThat(result).isEqualTo(21);
    }
}
