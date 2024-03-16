package domain.card.state;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Cards;
import org.junit.jupiter.api.Test;

class BustTest {
    @Test
    void 카드를_추가하면_예외가_발생한다() {
        CardState bust = new Bust(new Cards());

        assertThatThrownBy(() -> bust.receive(카드()))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void 끝내면_현재_상태를_리턴한다() {
        CardState bust = new Bust(new Cards());

        CardState finish = bust.finish();

        assertThat(finish).isExactlyInstanceOf(Bust.class);
    }

    @Test
    void 종료된_상태이다() {
        CardState bust = new Bust(new Cards());

        boolean finished = bust.isFinished();

        assertThat(finished).isTrue();
    }

    @Test
    void 버스트의_수익은_배팅_금액을_모두_잃는다() {
        CardState bust = new Bust(new Cards());

        int profit = bust.calculateProfit(1000);

        assertThat(profit).isEqualTo(-1000);
    }
}
