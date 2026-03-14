package domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import domain.TestFixture;
import domain.card.vo.Card;
import domain.card.vo.Rank;
import domain.card.vo.Suit;
import domain.state.finished.Blackjack;
import domain.state.finished.Bust;
import domain.state.running.Hit;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


class RunningTest {


    public static Stream<Arguments> getStartState() {
        return Stream.of(
                Arguments.of(TestFixture.getStartState(List.of(Rank.ACE, Rank.KING)), Blackjack.class),
                Arguments.of(TestFixture.getStartState(List.of(Rank.SIX, Rank.KING)), Hit.class)
        );
    }

    @ParameterizedTest
    @DisplayName("getStartState(): 첫 카드가 21이라면 블랙잭을 아니라면 Hit를 반환한다.")
    @MethodSource
    void getStartState(State state, Class<?> clazz) {
        assertThat(state).isInstanceOf(clazz);
    }

    @Nested
    @DisplayName("drawCard(): ")
    class DrawCard {
        public static Stream<Arguments> drawCard() {
            return Stream.of(
                    //21이 넘는다면 버스트를 반환한다.
                    Arguments.of(TestFixture.getStartState(List.of(Rank.KING, Rank.KING)),
                            new Card(Rank.KING, Suit.SPADE), Bust.class),
                    //21이 넘지 않는다면 다시 Hit을 반환하여 다음 상태에 대기한다.
                    Arguments.of(TestFixture.getStartState(List.of(Rank.ACE, Rank.ACE)),
                            new Card(Rank.KING, Suit.SPADE), Hit.class)
            );
        }

        @ParameterizedTest(name = "{3}: {4} draw K(10) -> '{'{2}'}'")
        @DisplayName("힛 여부에 따라 다음 상태를 반환한다.")
        @MethodSource
        void drawCard(State state, Card card, Class<?> clazz) {
            State nextState = state.drawCard(card);
            assertThat(nextState).isInstanceOf(clazz);
        }
    }
}
