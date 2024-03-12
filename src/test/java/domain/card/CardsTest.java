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

        Score score = cards.sumAllCards();

        assertThat(score).isEqualTo(Score.get(16));
    }

    @Test
    void Ace_6은_17이다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.ACE));
        cards.addCard(카드(Denomination.SIX));

        Score result = cards.sumAllCards();

        assertThat(result).isEqualTo(Score.get(17));
    }

    @Test
    void Ace_Ace는_12이다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.ACE));
        cards.addCard(카드(Denomination.ACE));

        Score result = cards.sumAllCards();

        assertThat(result).isEqualTo(Score.get(12));
    }

    @Test
    void King_Jack_Ace는_21이다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.ACE));
        cards.addCard(카드(Denomination.KING));
        cards.addCard(카드(Denomination.JACK));

        Score result = cards.sumAllCards();

        assertThat(result).isEqualTo(Score.get(21));
    }

    @Test
    void 카드가_2장이고_합이_21이면_블랙잭이다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.ACE));
        cards.addCard(카드(Denomination.KING));

        assertThat(cards.isBlackjack()).isTrue();
    }
}
