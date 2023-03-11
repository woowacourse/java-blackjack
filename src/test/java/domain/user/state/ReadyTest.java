package domain.user.state;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.JACK;
import static domain.card.Denomination.TWO;
import static domain.card.Suits.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @DisplayName("처음 한 장만 받으면 Ready상태이다.")
    @Test
    void stateToReady() {
        State state = new Ready()
            .draw(Card.of(JACK, DIAMOND));

        assertThat(state).isInstanceOf(Ready.class);
    }

    @DisplayName("처음 두 장을 받으면 Running상태로 변화한다.")
    @Test
    void stateToRunning() {
        State state = new Ready()
            .draw(Card.of(JACK, DIAMOND))
            .draw(Card.of(TWO, DIAMOND));

        assertThat(state).isInstanceOf(Running.class);
    }

    @DisplayName("처음 두 장 카드 합이 21이면 BlackJack 상태로 변화한다.")
    @Test
    void stateToBlackJack() {
        State state = new Ready()
            .draw(Card.of(ACE, DIAMOND))
            .draw(Card.of(JACK, DIAMOND));

        assertThat(state).isInstanceOf(BlackJack.class);
    }

    @DisplayName("카드를 더 받을 수 있다.")
    @Test
    void isDrawable() {
        State state = new Ready();

        assertThat(state.isDrawable()).isTrue();
    }

    @DisplayName("stay할시 예외가 발생한다.")
    @Test
    void stayException() {
        State state = new Ready();

        assertThatThrownBy(state::stay)
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("게임 시작 전입니다.");
    }
}
