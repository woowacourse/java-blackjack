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
    void 카드의_합을_계산한다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.TEN));
        cards.addCard(카드(Denomination.SIX));

        Score score = cards.sumAllCards();

        assertThat(score).isEqualTo(Score.get(16));
    }

    @Test
    void 카드가_없을_때_합을_계산하면_예외가_발생한다() {
        Cards cards = new Cards();

        assertThatThrownBy(cards::sumAllCards)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("카드가 존재하지 않습니다.");
    }

    @Test
    void Ace의_보너스_점수를_더했을_때_버스트가_아니면_보너스_점수를_더한다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.ACE));
        cards.addCard(카드(Denomination.SIX));

        Score result = cards.sumAllCards();

        assertThat(result).isEqualTo(Score.get(17));
    }

    @Test
    void Ace의_보너스_점수를_더했을_때_버스트면_보너스_점수를_더하지_않는다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.NINE));
        cards.addCard(카드(Denomination.FOUR));
        cards.addCard(카드(Denomination.ACE));

        Score result = cards.sumAllCards();

        assertThat(result).isEqualTo(Score.get(14));
    }

    @Test
    void Ace가_두_장이면_합은_12이다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.ACE));
        cards.addCard(카드(Denomination.ACE));

        Score result = cards.sumAllCards();

        assertThat(result).isEqualTo(Score.get(12));
    }

    @Test
    void 카드가_3장이고_합이_21이면_블랙잭이_아니다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.KING));
        cards.addCard(카드(Denomination.JACK));
        cards.addCard(카드(Denomination.ACE));

        Score result = cards.sumAllCards();

        assertThat(result).isEqualTo(Score.get(21));
        assertThat(cards.isBlackjack()).isFalse();
    }

    @Test
    void 카드가_2장이고_합이_21이면_블랙잭이다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.ACE));
        cards.addCard(카드(Denomination.KING));

        assertThat(cards.isBlackjack()).isTrue();
    }

    @Test
    void 카드의_합이_bust가_아니면_false를_반환한다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.KING));
        cards.addCard(카드(Denomination.JACK));
        cards.addCard(카드(Denomination.ACE));

        assertThat(cards.isBust()).isFalse();
    }

    @Test
    void 카드의_합이_bust면_true를_반환한다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.KING));
        cards.addCard(카드(Denomination.JACK));
        cards.addCard(카드(Denomination.TWO));

        assertThat(cards.isBust()).isTrue();
    }
}
