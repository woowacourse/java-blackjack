package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RefereeTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("judgeTestCases")
    @DisplayName("승패를 올바르게 판단한다.")
    void judge(String description, List<Integer> dealerCards, List<Integer> playerCards, boolean expected) {
        Dealer dealer = createDealer(dealerCards);
        Player player = Player.from("pobi");
        playerCards.forEach(number -> player.receiveCard(new Card(Shape.HEART, Number.from(number))));

        Result result = new Referee().judge(dealer, List.of(player));
        assertThat(result.getPlayerResults().get("pobi")).isEqualTo(expected);
    }

    static Stream<Arguments> judgeTestCases() {
        return Stream.of(
                Arguments.of("플레이어 버스트면 진다", List.of(10, 8), List.of(10, 10, 10), false),
                Arguments.of("딜러 버스트면 플레이어가 이긴다", List.of(10, 10, 10), List.of(10, 8), true),
                Arguments.of("플레이어 점수가 높으면 이긴다", List.of(10, 7), List.of(10, 9), true),
                Arguments.of("플레이어 점수가 낮으면 진다", List.of(10, 9), List.of(10, 7), false),
                Arguments.of("동점이면 딜러가 이긴다", List.of(10, 9), List.of(10, 9), false)
        );
    }

    private Dealer createDealer(List<Integer> numbers) {
        Dealer dealer = Dealer.create();
        numbers.forEach(n -> dealer.receiveCard(new Card(Shape.HEART, Number.from(n))));
        return dealer;
    }
}