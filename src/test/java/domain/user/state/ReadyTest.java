package domain.user.state;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.JACK;
import static domain.card.Denomination.TEN;
import static domain.card.Denomination.TWO;
import static domain.card.Suits.DIAMOND;
import static domain.card.Suits.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    State state;

    @BeforeEach
    void setUpReadyState() {
        state = new Ready();
    }

    @DisplayName("처음 한 장만 받으면 Ready상태이다.")
    @Test
    void stateToReady() {
        State newState = state.draw(Card.of(JACK, DIAMOND));

        assertThat(newState).isInstanceOf(Ready.class);
    }

    @DisplayName("처음 두 장을 받으면 Running상태로 변화한다.")
    @Test
    void stateToRunning() {
        State newState = state
            .draw(Card.of(JACK, DIAMOND))
            .draw(Card.of(TWO, DIAMOND));

        assertThat(newState).isInstanceOf(Running.class);
    }

    @DisplayName("처음 두 장 카드 합이 21이면 BlackJack 상태로 변화한다.")
    @Test
    void stateToBlackJack() {
        State newState = state
            .draw(Card.of(ACE, DIAMOND))
            .draw(Card.of(JACK, DIAMOND));

        assertThat(newState).isInstanceOf(BlackJack.class);
    }

    @DisplayName("카드를 더 받을 수 있다.")
    @Test
    void isDrawable() {
        assertThat(state.isDrawable()).isTrue();
    }

    @DisplayName("stay할시 예외가 발생한다.")
    @Test
    void stayException() {
        assertThatThrownBy(state::stay)
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessage("게임 시작 전입니다.");
    }

    @DisplayName("딜러와 점수 비교시 예외가 발생한다.")
    @Test
    void match() {
        State dealer = new Ready()
            .draw(Card.of(ACE, HEART))
            .draw(Card.of(TEN, HEART));
        assertThatThrownBy(() -> state.match(dealer))
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessage("게임 종료 전입니다.");
    }

    @DisplayName("블랙잭 확인시 예외가 발생한다.")
    @Test
    void isBlackJack() {
        assertThatThrownBy(() -> state.isBlackJack())
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessage("게임 종료 전입니다.");
    }

    @DisplayName("버스트 확인시 예외가 발생한다.")
    @Test
    void isBust() {
        assertThatThrownBy(() -> state.isBust())
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessage("게임 종료 전입니다.");
    }
}
