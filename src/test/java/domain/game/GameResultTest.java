package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParameters")
    @DisplayName("플레이어 승리")
    void test(String comment, Player player, Dealer dealer) {
        // given
        GameResult gameResult = new GameResult(List.of(player), dealer);

        // then
        assertThat(gameResult.getMatchResult(player)).isEqualTo(MatchResult.WIN);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments("플레이어가 버스트가 아니고 딜러가 버스트인 경우",
                        new Player(new Name("abc"), getCards(Denomination.ACE, Denomination.NINE)),
                        new Dealer(new Name("딜러"), getCards(Denomination.QUEEN, Denomination.KING, Denomination.JACK))),
                Arguments.arguments("둘 다 버스트가 아니고 딜러보다 숫자가 높은 경우",
                        new Player(new Name("abc"), getCards(Denomination.KING, Denomination.QUEEN)),
                        new Dealer(new Name("딜러"), getCards(Denomination.QUEEN, Denomination.NINE)))
        );
    }

    @ParameterizedTest(name = "{3} 개수 -> {4}회")
    @MethodSource("provideParameters2")
    @DisplayName("딜러가 승리한 횟수")
    void dealer_count_test(Player player1, Player player2, Dealer dealer, MatchResult result, int expect) {
        // given
        List<Participant> players = Arrays.asList(player1, player2);
        GameResult gameResult = new GameResult(players, dealer);

        // then
        assertThat(gameResult.calculateDealerMatchResultCount(result)).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters2() {
        return Stream.of(
                Arguments.arguments(
                        new Player(new Name("abc1"), getCards(Denomination.ACE, Denomination.NINE)),
                        new Player(new Name("abc2"), getCards(Denomination.EIGHT, Denomination.NINE)),
                        new Dealer(new Name("딜러"), getCards(Denomination.QUEEN, Denomination.NINE)),
                        MatchResult.WIN,
                        1
                ),
                Arguments.arguments(
                        new Player(new Name("abc1"), getCards(Denomination.ACE, Denomination.NINE)),
                        new Player(new Name("abc2"), getCards(Denomination.NINE, Denomination.NINE)),
                        new Dealer(new Name("딜러"), getCards(Denomination.EIGHT, Denomination.NINE)),
                        MatchResult.LOSE,
                        2
                )
        );
    }

    private static Cards getCards(Denomination... arguments) {
        List<Card> list = new ArrayList<>();
        for (Denomination denomination : arguments) {
            list.add(new Card(denomination, Suit.CLOVER));
        }
        return new Cards(list);
    }
}
