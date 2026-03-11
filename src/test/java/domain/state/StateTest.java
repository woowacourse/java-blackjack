package domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import domain.TestFixture;
import domain.card.vo.Rank;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StateTest {


    public static Stream<Arguments> getStartState() {
        return Stream.of(
                Arguments.of(TestFixture.createDefaultPlayerStateByRank(List.of(Rank.ACE, Rank.KING)), BlackJack.class),
                Arguments.of(TestFixture.createDefaultDealerState(List.of(Rank.ACE, Rank.KING)), BlackJack.class),
                Arguments.of(TestFixture.createDefaultPlayerStateByRank(List.of(Rank.SIX, Rank.KING)), Hit.class),
                Arguments.of(TestFixture.createDefaultDealerState(List.of(Rank.SIX, Rank.KING)), Hit.class)

        );
    }

    @ParameterizedTest
    @DisplayName("getStartState(): 첫 카드가 21이라면 블랙잭을 아니라면 Hit를 반환한다.")
    @MethodSource
    void getStartState(State state, Class<?> clazz) {
        assertThat(state).isInstanceOf(clazz);
    }
}
