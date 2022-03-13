package blackjack.domain.state.finished;

import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.FOUR;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardPattern.SPADE;
import static blackjack.domain.game.GameOutcome.DRAW;
import static blackjack.domain.game.GameOutcome.LOSE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.GameOutcome;
import blackjack.domain.state.State;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BustTest {
    @ParameterizedTest
    @DisplayName("자신과 다른 State 비교해 승부 결과를 반환한다.")
    @MethodSource("provideStateAndExpected")
    void compare(State another, GameOutcome expected) {
        State state = new Bust(
                new Cards(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, QUEEN), Card.of(SPADE, JACK))));
        assertThat(state.compare(another)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideStateAndExpected() {
        final Cards blackJackCards = new Cards(Collections.singletonList(Card.of(SPADE, A)));
        final Cards stayCards = new Cards(Arrays.asList(Card.of(SPADE, A), Card.of(SPADE, FOUR)));
        final Cards bustCards =
                new Cards(Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, QUEEN), Card.of(SPADE, JACK)));

        return Stream.of(
                Arguments.of(new BlackJack(blackJackCards), LOSE),
                Arguments.of(new Stay(stayCards), LOSE),
                Arguments.of(new Bust(bustCards), DRAW)
        );
    }
}
