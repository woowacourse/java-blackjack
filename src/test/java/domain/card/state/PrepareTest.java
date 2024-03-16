package domain.card.state;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Cards;
import domain.card.Denomination;
import org.junit.jupiter.api.Test;

class PrepareTest {
    @Test
    void 카드를_추가했을_때_blackjack이_되면_blackjack_상태를_리턴한다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.ACE));
        CardState started = new Prepare(cards);

        CardState blackjack = started.receive(카드(Denomination.TEN));

        assertThat(blackjack).isExactlyInstanceOf(Blackjack.class);
    }

    @Test
    void 카드를_추가했을_때_2장_이상이면_hit_상태를_리턴한다() {
        Cards cards = new Cards();
        cards.addCard(카드(Denomination.JACK));
        CardState started = new Prepare(cards);

        CardState hit = started.receive(카드(Denomination.TEN));

        assertThat(hit).isExactlyInstanceOf(Hit.class);
    }

    @Test
    void 맨_처음_카드를_추가하면_현재_상태를_리턴한다() {
        Cards cards = new Cards();
        CardState started = new Prepare(cards);
        CardState next = started.receive(카드());

        assertThat(next).isExactlyInstanceOf(Prepare.class);
    }

    @Test
    void 끝내면_예외가_발생한다() {
        Cards cards = new Cards();
        CardState started = new Prepare(cards);

        assertThatThrownBy(started::finish)
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void 종료되지_않은_상태이다() {
        Cards cards = new Cards();
        CardState started = new Prepare(cards);

        boolean finished = started.isFinished();

        assertThat(finished).isFalse();
    }

    @Test
    void 수익을_계산하면_예외가_발생한다() {
        Cards cards = new Cards();
        CardState started = new Prepare(cards);

        assertThatThrownBy(() -> started.calculateProfit(1))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }
}
