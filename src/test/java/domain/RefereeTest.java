package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RefereeTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("playerScoreTestCases")
    @DisplayName("플레이어의 점수가 딜러보다 높으면 이기고, 낮으면 진다. 또한 동점이면 딜러가 이긴다.")
    void player_score_compare(String description, List<Integer> dealerCards, List<Integer> playerCards,
                              boolean expected) {
        Dealer dealer = createDealer(dealerCards);
        Player player = Player.from("pobi");
        playerCards.forEach(number -> player.receiveCard(new Card(Shape.HEART, Number.from(number))));

        Result result = new Referee().judge(dealer, List.of(player));
        assertThat(result.getPlayerResults().get("pobi")).isEqualTo(expected);
    }

    static Stream<Arguments> playerScoreTestCases() {
        return Stream.of(Arguments.of("플레이어 점수가 높으면 이긴다", List.of(10, 7), List.of(10, 9), true),
                Arguments.of("플레이어 점수가 낮으면 진다", List.of(10, 9), List.of(10, 7), false),
                Arguments.of("동점이면 딜러가 이긴다", List.of(10, 9), List.of(10, 9), false));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("playerBustTestCases")
    @DisplayName("플레이어의 점수가 버스트되면 딜러의 점수와 무관하게 패배한다.")
    void judge(String description, List<Integer> dealerCards, List<Integer> playerCards, boolean expected) {
        Dealer dealer = createDealer(dealerCards);
        Player player = Player.from("pobi");
        playerCards.forEach(number -> player.receiveCard(new Card(Shape.HEART, Number.from(number))));

        Result result = new Referee().judge(dealer, List.of(player));
        assertThat(result.getPlayerResults().get("pobi")).isEqualTo(expected);
    }

    static Stream<Arguments> playerBustTestCases() {
        return Stream.of(Arguments.of("플레이어 버스트면 진다", List.of(10, 8), List.of(10, 10, 10), false),
                Arguments.of("딜러의 점수가 버스트되었더라도 플레이어의 점수가 버스트되면 플레이어의 패배다.", List.of(10, 8, 10), List.of(10, 10, 10),
                        false));
    }

    @Test
    @DisplayName("딜러가 버스트면 플레이어가 이긴다.")
    void dealer_bust() {
        Dealer dealer = createDealer(List.of(10, 10, 10));
        Player player = Player.from("pobi");
        player.receiveCard(new Card(Shape.HEART, Number.from(10)));
        player.receiveCard(new Card(Shape.HEART, Number.from(8)));

        Result result = new Referee().judge(dealer, List.of(player));
        assertThat(result.getPlayerResults().get("pobi")).isTrue();
    }

    private Dealer createDealer(List<Integer> numbers) {
        Dealer dealer = Dealer.create();
        numbers.forEach(n -> dealer.receiveCard(new Card(Shape.HEART, Number.from(n))));
        return dealer;
    }
}
