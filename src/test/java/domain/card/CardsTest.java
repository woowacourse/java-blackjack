package domain.card;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CardsTest {
    @Test
    void 카드를_한_장_받는다() {
        Cards cards = new Cards();
        cards.addCard(카드());

        assertThat(cards.getCards()).hasSize(1);
    }

    @Test
    void Bust에_카드를_받으면_예외가_발생한다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.TEN));
        cards.addCard(카드(Denomination.JACK));
        cards.addCard(카드(Denomination.QUEEN));

        assertThatThrownBy(() -> cards.addCard(카드(Denomination.KING)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 카드의_합을_계산한다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.TEN));
        cards.addCard(카드(Denomination.SIX));

        Score score = cards.sumAllScore();

        assertThat(score).isEqualTo(Score.get(16));
    }

    @Test
    void Ace는_1_또는_11로_계산할_수_있다_1() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.ACE));
        cards.addCard(카드(Denomination.SIX));

        Score result = cards.sumAllScore();

        assertThat(result).isEqualTo(Score.get(17));
    }

    @Test
    void Ace는_1_또는_11로_계산할_수_있다_2() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.ACE));
        cards.addCard(카드(Denomination.ACE));

        Score result = cards.sumAllScore();

        assertThat(result).isEqualTo(Score.get(12));
    }

    @Test
    void Ace는_1_또는_11로_계산할_수_있다_3() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.ACE));
        cards.addCard(카드(Denomination.KING));
        cards.addCard(카드(Denomination.JACK));

        Score result = cards.sumAllScore();

        assertThat(result).isEqualTo(Score.get(21));
    }
}
