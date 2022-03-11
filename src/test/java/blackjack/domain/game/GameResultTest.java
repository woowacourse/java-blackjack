package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
        GameResult gameResult = new GameResult(new Players(Arrays.asList(player)), dealer);
        assertThat(gameResult.getMatchResult(player)).isEqualTo(MatchResult.WIN);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments("플레이어가 버스트가 아니고 딜러가 버스트인 경우",
                        new Player(new Name("abc"),
                                new Cards(getCardList(Denomination.ACE, Denomination.NINE))),
                        new Dealer(new Name("딜러"),
                                new Cards(getCardList(Denomination.QUEEN, Denomination.KING, Denomination.JACK)))),
                Arguments.arguments("둘 다 버스트가 아니고 딜러보다 숫자가 높은 경우",
                        new Player(new Name("abc"),
                                new Cards(getCardList(Denomination.KING, Denomination.QUEEN))),
                        new Dealer(new Name("딜러"),
                                new Cards(getCardList(Denomination.QUEEN, Denomination.NINE))))
        );
    }

    @Test
    @DisplayName("딜러가 승리한 횟수")
    void dealer_win_count_test() {
        Player player1 = new Player(new Name("abc1"),
                new Cards(getCardList(Denomination.ACE, Denomination.NINE)));      // 20
        Player player2 = new Player(new Name("abc2"),
                new Cards(getCardList(Denomination.EIGHT, Denomination.NINE)));    // 17
        Player player3 = new Player(new Name("abc3"),
                new Cards(getCardList(Denomination.ACE, Denomination.NINE)));      // 20
        Dealer dealer = new Dealer(new Name("딜러"),
                new Cards(getCardList(Denomination.QUEEN, Denomination.NINE)));    // 19
        Players players = new Players(Arrays.asList(player1, player2, player3));
        GameResult gameResult = new GameResult(players, dealer);

        assertThat(gameResult.getDealerWinCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 패배한 횟수")
    void dealer_lose_count_test() {
        Player player1 = new Player(new Name("abc1"),
                new Cards(getCardList(Denomination.ACE, Denomination.NINE)));      // 20
        Player player2 = new Player(new Name("abc2"),
                new Cards(getCardList(Denomination.EIGHT, Denomination.NINE)));    // 17
        Player player3 = new Player(new Name("abc3"),
                new Cards(getCardList(Denomination.ACE, Denomination.NINE)));      // 20
        Dealer dealer = new Dealer(new Name("딜러"),
                new Cards(getCardList(Denomination.QUEEN, Denomination.NINE)));    // 19

        Players players = new Players(Arrays.asList(player1, player2, player3));
        GameResult gameResult = new GameResult(players, dealer);

        assertThat(gameResult.getDealerLoseCount()).isEqualTo(2);
    }

    private static List<Card> getCardList(Denomination... arguments) {
        List<Card> list = new ArrayList<>();
        for (Denomination denomination : arguments) {
            list.add(Card.valueOf(denomination, Suit.CLOVER));
        }
        return list;
    }
}
