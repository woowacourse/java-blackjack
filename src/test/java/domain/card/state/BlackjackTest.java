package domain.card.state;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Cards;
import org.junit.jupiter.api.Test;

class BlackjackTest {
    @Test
    void 카드를_추가하면_예외가_발생한다() {
        CardState blackjack = new Blackjack(new Cards());

        assertThatThrownBy(() -> blackjack.receive(카드()))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void 끝내면_현재_상태를_리턴한다() {
        CardState blackjack = new Blackjack(new Cards());

        CardState finish = blackjack.finish();

        assertThat(finish).isExactlyInstanceOf(Blackjack.class);
    }

    @Test
    void 종료된_상태이다() {
        CardState blackjack = new Blackjack(new Cards());

        boolean finished = blackjack.isFinished();

        assertThat(finished).isTrue();
    }

    @Test
    void 블랙잭의_수익은_배팅_금액의_150퍼센트이다() {
        CardState blackjack = new Blackjack(new Cards());

        int profit = blackjack.calculateProfit(1000);

        assertThat(profit).isEqualTo(1500);
    }
}
