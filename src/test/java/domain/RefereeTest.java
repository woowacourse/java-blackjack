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
    @DisplayName("플레이어의 점수가 딜러보다 높으면 이기고, 낮으면 진다.")
    void player_score_compare(String description, List<Number> dealerCards, List<Number> playerCards,
                              MatchResult expected) {
        Dealer dealer = createDealer(dealerCards);
        Player player = createPlayer();
        playerCards.forEach(number -> player.receiveCard(new Card(Shape.HEART, number)));

        Result result = new Referee().judge(dealer, List.of(player));
        assertThat(result.getPlayerResults().get(player)).isEqualTo(expected);
    }

    static Stream<Arguments> playerScoreTestCases() {
        return Stream.of(
                Arguments.of("플레이어 점수가 높으면 이긴다",
                        List.of(Number.TEN, Number.SEVEN),
                        List.of(Number.TEN, Number.NINE),
                        MatchResult.WIN),
                Arguments.of("플레이어 점수가 낮으면 진다",
                        List.of(Number.TEN, Number.NINE),
                        List.of(Number.TEN, Number.SEVEN),
                        MatchResult.LOSE));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("playerBustTestCases")
    @DisplayName("플레이어의 점수가 버스트되면 딜러의 점수와 무관하게 패배한다.")
    void player_bust(String description, List<Number> dealerCards, List<Number> playerCards, MatchResult expected) {
        Dealer dealer = createDealer(dealerCards);
        Player player = createPlayer();
        playerCards.forEach(number -> player.receiveCard(new Card(Shape.HEART, number)));

        Result result = new Referee().judge(dealer, List.of(player));
        assertThat(result.getPlayerResults().get(player)).isEqualTo(expected);
    }

    static Stream<Arguments> playerBustTestCases() {
        return Stream.of(
                Arguments.of("플레이어 버스트면 진다",
                        List.of(Number.TEN, Number.EIGHT),
                        List.of(Number.TEN, Number.TEN, Number.TEN),
                        MatchResult.LOSE),
                Arguments.of("딜러의 점수가 버스트되었더라도 플레이어의 점수가 버스트되면 플레이어의 패배다.",
                        List.of(Number.TEN, Number.EIGHT, Number.TEN),
                        List.of(Number.TEN, Number.TEN, Number.TEN),
                        MatchResult.LOSE));
    }

    @Test
    @DisplayName("딜러가 버스트면 플레이어가 이긴다.")
    void dealer_bust() {
        Dealer dealer = createDealer(List.of(Number.TEN, Number.JACK, Number.QUEEN));
        Player player = createPlayer();
        player.receiveCard(new Card(Shape.HEART, Number.TEN));
        player.receiveCard(new Card(Shape.HEART, Number.EIGHT));

        Result result = new Referee().judge(dealer, List.of(player));
        assertThat(result.getPlayerResults().get(player)).isEqualTo(MatchResult.WIN);
    }

    @Test
    @DisplayName("동점이면 무승부다.")
    void player_dealer_draw() {
        Dealer dealer = createDealer(List.of(Number.TEN, Number.NINE));
        Player player = createPlayer();
        player.receiveCard(new Card(Shape.HEART, Number.TEN));
        player.receiveCard(new Card(Shape.HEART, Number.NINE));

        Result result = new Referee().judge(dealer, List.of(player));
        assertThat(result.getPlayerResults().get(player)).isEqualTo(MatchResult.DRAW);
    }

    @Test
    @DisplayName("플레이어만 블랙잭이면 플레이어의 승리이다.")
    void player_blackjack() {
        Dealer dealer = createDealer(List.of(Number.TEN, Number.NINE));
        Player player = createPlayer();
        player.receiveCard(new Card(Shape.HEART, Number.ACE));
        player.receiveCard(new Card(Shape.HEART, Number.TEN));

        Result result = new Referee().judge(dealer, List.of(player));
        assertThat(result.getPlayerResults().get(player)).isEqualTo(MatchResult.BLACKJACK);
    }

    @Test
    @DisplayName("딜러만 블랙잭이면 플레이어의 패배다.")
    void dealer_blackjack() {
        Dealer dealer = createDealer(List.of(Number.TEN, Number.ACE));
        Player player = createPlayer();
        player.receiveCard(new Card(Shape.HEART, Number.TEN));
        player.receiveCard(new Card(Shape.HEART, Number.TEN));

        Result result = new Referee().judge(dealer, List.of(player));
        assertThat(result.getPlayerResults().get(player)).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 블랙잭이면 무승부다.")
    void both_blackjack() {
        Dealer dealer = createDealer(List.of(Number.ACE, Number.TEN));
        Player player = createPlayer();
        player.receiveCard(new Card(Shape.HEART, Number.ACE));
        player.receiveCard(new Card(Shape.HEART, Number.TEN));

        Result result = new Referee().judge(dealer, List.of(player));
        assertThat(result.getPlayerResults().get(player)).isEqualTo(MatchResult.DRAW);
    }

    @Test
    @DisplayName("딜러만 블랙잭이고 플레이어가 일반 21점이면 플레이어의 패배이다.")
    void dealer_blackjack_player_not_blackjack() {
        Dealer dealer = createDealer(List.of(Number.ACE, Number.TEN));
        Player player = createPlayer();
        player.receiveCard(new Card(Shape.HEART, Number.ACE));
        player.receiveCard(new Card(Shape.HEART, Number.TEN));
        player.receiveCard(new Card(Shape.HEART, Number.QUEEN));

        Result result = new Referee().judge(dealer, List.of(player));
        assertThat(result.getPlayerResults().get(player)).isEqualTo(MatchResult.LOSE);
    }

    private Player createPlayer() {
        return Player.from("pobi", new Money(10000));
    }

    private Dealer createDealer(List<Number> numbers) {
        Dealer dealer = Dealer.create();
        numbers.forEach(number -> dealer.receiveCard(new Card(Shape.HEART, number)));
        return dealer;
    }
}
