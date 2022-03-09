package domain.game;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
import domain.card.Suit;
import domain.player.Dealer;
import domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParameters")
    @DisplayName("플레이어 승리")
    void test(String comment, Player player, Dealer dealer) {
        GameResult gameResult = new GameResult(Arrays.asList(player), dealer);
        assertThat(gameResult.getMatchResult(player)).isEqualTo(MatchResult.WIN);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments("플레이어가 버스트가 아니고 딜러가 버스트인 경우",
                        new Player("abc", new Cards(getCardList(Denomination.ACE, Denomination.NINE))),
                        new Dealer(new Cards(getCardList(Denomination.QUEEN, Denomination.KING, Denomination.JACK)))),
                Arguments.arguments("둘 다 버스트가 아니고 딜러보다 숫자가 높은 경우",
                        new Player("abc", new Cards(getCardList(Denomination.KING, Denomination.QUEEN))),
                        new Dealer(new Cards(getCardList(Denomination.QUEEN, Denomination.NINE))))
        );
    }

    private static List<Card> getCardList(Denomination... arguments) {
        List<Card> list = new ArrayList<>();
        for (Denomination denomination : arguments) {
            list.add(new Card(denomination, Suit.CLOVER));
        }
        return list;
    }
}
