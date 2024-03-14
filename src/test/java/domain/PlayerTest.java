package domain;

import static domain.HandsTestFixture.bustHands;
import static domain.HandsTestFixture.sum18Size2;
import static domain.HandsTestFixture.sum20Size2;
import static domain.HandsTestFixture.sum21Size3Ace11;

import domain.participant.Hands;
import domain.participant.Name;
import domain.participant.Player;
import exception.ReservedPlayerNameException;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    @DisplayName("이름으로 참여자를 생성한다.")
    @Test
    void createPlayerWithName() {
        Assertions.assertThatCode(() -> new Player(new Name("pobi"), Hands.createEmptyHands()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("참여자 이름이 딜러이면 예외가 발생한다.")
    void validateName() {
        final Name name = new Name("딜러");
        final Hands hands = Hands.createEmptyHands();

        Assertions.assertThatThrownBy(() -> new Player(name, hands))
                .isInstanceOf(ReservedPlayerNameException.class);
    }

    @ParameterizedTest
    @DisplayName("참여자가 21 이상이면 더이상 딜을 할 수가 없다.")
    @MethodSource("canNotDealParameterProvider")
    void canNotDeal(Hands hands) {
        final Name name = new Name("레디");
        final Player player = new Player(name, hands);
        Assertions.assertThat(player.canDeal()).isFalse();
    }

    @ParameterizedTest
    @DisplayName("참여자가 21 이하라면 딜 할 수가 있다.")
    @MethodSource("canDealParameterProvider")
    void canDeal(Hands hands) {
        final Name name = new Name("레디");
        final Player player = new Player(name, hands);
        Assertions.assertThat(player.canDeal()).isTrue();
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
