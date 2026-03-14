package domain.participants;

import static org.assertj.core.api.Assertions.assertThat;

import domain.TestFixture;
import domain.card.vo.Rank;
import domain.state.finished.Blackjack;
import domain.state.finished.Stay;
import domain.state.running.Hit;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    public static Stream<Arguments> canDraw() {
        return Stream.of(
                Arguments.of(TestFixture.createDefaultDealer(List.of(Rank.KING, Rank.SEVEN)), Stay.class),
                Arguments.of(TestFixture.createDefaultDealer(List.of(Rank.KING, Rank.SIX)), Hit.class),
                Arguments.of(TestFixture.createDefaultDealer(List.of(Rank.KING, Rank.ACE)), Blackjack.class)
        );
    }

    @ParameterizedTest
    @MethodSource
    void canDraw(Dealer dealer, Class<?> clazz) {
        assertThat(dealer.getState()).isInstanceOf(clazz);
    }


}
