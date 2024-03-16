package domain.card.state;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Cards;
import org.junit.jupiter.api.Test;

class StayTest {
    @Test
    void 카드를_추가하면_예외가_발생한다() {
        CardState stay = new Stay(new Cards());

        assertThatThrownBy(() -> stay.receive(카드()))
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("스테이 상태에서는 카드를 추가할 수 없습니다.");
    }

    @Test
    void 끝내면_현재_상태를_리턴한다() {
        CardState stay = new Stay(new Cards());

        CardState finish = stay.finish();

        assertThat(finish).isExactlyInstanceOf(Stay.class);
    }
}
