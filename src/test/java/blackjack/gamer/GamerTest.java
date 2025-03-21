package blackjack.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Hand;
import blackjack.state.State;
import blackjack.state.started.ready.Ready;
import fixture.BettingFixture;
import fixture.NicknameFixture;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GamerTest {

    public static Stream<Arguments> provideInstancesOfGamer() {
        return Stream.of(
                Arguments.of(
                        new Player(new Hand(),
                                NicknameFixture.createNickname("ad"),
                                BettingFixture.createBetting(1000)),
                        new Dealer(new Hand())
                ));
    }

    @DisplayName("Gamer는 생성시에 준비 상태를가진다")
    @ParameterizedTest
    @MethodSource("provideInstancesOfGamer")
    void isReady(Gamer gamer) {
        // when
        State actual = gamer.state;

        // then
        assertThat(actual).isInstanceOf(Ready.class);
    }
}
