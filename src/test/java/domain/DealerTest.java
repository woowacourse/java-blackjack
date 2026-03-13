package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("canReceiveCardTestCases")
    @DisplayName("딜러가 카드를 받을 수 있는지 확인한다.")
    void canReceiveCard(String description, List<Integer> scores, boolean expected) {
        Dealer dealer = Dealer.create();
        scores.forEach(score -> dealer.receiveCard(new Card(Shape.HEART, Number.from(score))));

        assertThat(dealer.canReceiveCard()).isEqualTo(expected);
    }

    static Stream<Arguments> canReceiveCardTestCases() {
        return Stream.of(
                Arguments.of("16 이하면 카드를 받을 수 있다", List.of(10, 6), true),
                Arguments.of("17이면 카드를 받을 수 없다", List.of(10, 7), false),
                Arguments.of("버스트면 카드를 받을 수 없다", List.of(10, 10, 10), false)
        );
    }
}