package blackjack.domain.state;

import static blackjack.domain.TestCardFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ReadyTest {

    @DisplayName("Ready 상태가 정상적으로 생성되는지 확인")
    @Test
    void create() {
        Ready ready = new Ready();

        assertThat(ready).isNotNull();
    }

    @DisplayName("Ready 상태면 Running상태가 아니다")
    @Test
    void doesNotRunning() {
        Ready ready = new Ready();

        assertThat(ready.isRunning()).isFalse();
    }

    @DisplayName("한장의 카드만 draw했을때 Ready상태여야 한다.")
    @Test
    void drawOneCard() {
        State state = new Ready();

        state.draw(aceCard);

        assertThat(state).isInstanceOf(Ready.class);
    }

    @DisplayName("카드를 드로우 할때 케이스별로 상태가 올바른지 확인")
    @ParameterizedTest
    @MethodSource("drawCase")
    void drawOtherCase(Card card, Class<State> excepted) {
        State state = new Ready();

        state = state.draw(aceCard);
        state = state.draw(card);

        assertThat(state).isInstanceOf(excepted);
    }

    private static Stream<Arguments> drawCase() {
        return Stream.of(
            Arguments.of(tenCard, Blackjack.class),
            Arguments.of(fiveCard, Hit.class)
        );
    }

    @DisplayName("스테이를 실행하면 예외 발생")
    @Test
    void throwExceptionWhenStay() {
        State state = new Ready();

        assertThatThrownBy(state::stay)
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessageContaining("[ERROR] 스테이를 지원하지 않습니다.");
    }
}

