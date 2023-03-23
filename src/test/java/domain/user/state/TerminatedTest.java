package domain.user.state;

import static domain.card.Denomination.JACK;
import static domain.card.Denomination.KING;
import static domain.card.Denomination.QUEEN;
import static domain.card.Denomination.TEN;
import static domain.card.Suits.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TerminatedTest {

    State state;

    @BeforeEach
    void setUpTerminatedState() {
        this.state = State.create()
            .hit(Card.of(TEN, DIAMOND))
            .hit(Card.of(JACK, DIAMOND)) // Ready -> Running
            .hit(Card.of(QUEEN, DIAMOND)); // Running -> Terminated(Bust)
    }

    @DisplayName("카드를 더 받으려 하면 예외를 반환한다.")
    @Test
    void hitException() {
        assertThatThrownBy(() -> state.hit(Card.of(KING, DIAMOND)))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("카드를 더 받을 수 없다.")
    @Test
    void isHittable() {
        assertThat(state.isHittable()).isFalse();
    }

    @DisplayName("stay할시 예외가 발생한다.")
    @Test
    void stayException() {
        assertThatThrownBy(state::stay)
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessage("이미 종료되었습니다.");
    }
}
