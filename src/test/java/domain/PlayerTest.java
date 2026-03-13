package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class PlayerTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("canReceiveCardTestCases")
    @DisplayName("플레이어가 카드를 받을 수 있는지 확인한다.")
    void canReceiveCard(String description, List<Number> numbers, boolean expected) {
        Player player = Player.from("pobi");
        numbers.forEach(number -> player.receiveCard(new Card(Shape.HEART, number)));

        assertThat(player.canReceiveCard()).isEqualTo(expected);
    }

    static Stream<Arguments> canReceiveCardTestCases() {
        return Stream.of(
                Arguments.of("20 이하면 카드를 받을 수 있다", List.of(Number.QUEEN, Number.NINE), true),
                Arguments.of("21이면 카드를 받을 수 없다", List.of(Number.QUEEN, Number.ACE), false),
                Arguments.of("버스트면 카드를 받을 수 없다", List.of(Number.KING, Number.JACK, Number.TEN), false)
        );
    }
}
