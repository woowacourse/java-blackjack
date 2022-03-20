package blackjack.domain.state;

import static blackjack.domain.TestCardFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ReadyTest {

    @Test
    void create() {
        Ready ready = new Ready();

        assertThat(ready).isNotNull();
    }

    @Test
    void doesNotRunning() {
        Ready ready = new Ready();

        assertThat(ready.isRunning()).isFalse();
    }

    @Test
    void drawOneCard() {
        State state = new Ready();

        state.draw(aceCard);

        assertThat(state).isInstanceOf(Ready.class);
    }

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

    @Test
    void throwExceptionWhenStay() {
        State state = new Ready();

        assertThatThrownBy(state::stay)
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessageContaining("[ERROR] 스테이를 지원하지 않습니다.");
    }
}

