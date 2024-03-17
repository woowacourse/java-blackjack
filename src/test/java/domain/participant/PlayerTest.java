package domain.participant;

import static domain.participant.HandsTestFixture.bustHands;
import static domain.participant.HandsTestFixture.sum18Size2;
import static domain.participant.HandsTestFixture.sum20Size2;
import static domain.participant.HandsTestFixture.sum21Size3Ace11;
import static domain.amount.BetAmount.defaultBetAmount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    @DisplayName("이름으로 참여자를 생성한다.")
    @Test
    void createPlayerWithName() {
        assertThatCode(() -> new Player(new Name("pobi"), Hands.createEmptyHands(), defaultBetAmount))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("참여자가 21 이상이면 더이상 딜을 할 수가 없다.")
    @MethodSource("canNotDealParameterProvider")
    void canNotDeal(Hands hands) {
        final Name name = new Name("레디");
        final Player player = new Player(name, hands, defaultBetAmount);
        assertThat(player.canDeal()).isFalse();
    }

    @ParameterizedTest
    @DisplayName("참여자가 21 이하라면 딜 할 수가 있다.")
    @MethodSource("canDealParameterProvider")
    void canDeal(Hands hands) {
        final Name name = new Name("레디");
        final Player player = new Player(name, hands, defaultBetAmount);
        assertThat(player.canDeal()).isTrue();
    }

    static Stream<Arguments> canNotDealParameterProvider() {
        return Stream.of(
                Arguments.of(sum21Size3Ace11),
                Arguments.of(bustHands)
        );
    }

    static Stream<Arguments> canDealParameterProvider() {
        return Stream.of(
                Arguments.of(sum18Size2),
                Arguments.of(sum20Size2)
        );
    }
}
