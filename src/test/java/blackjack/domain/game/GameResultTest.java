package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestUtil.getCards;

import blackjack.domain.card.Number;
import blackjack.domain.participant.BettingAmount;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParameters")
    @DisplayName("수익 구하기")
    void bettingAmount(String comment, Player player, Dealer dealer, long expect) {
        // given
        List<Player> players = List.of(player);
        Participants participants = new Participants(players, dealer);
        GameResult gameResult = new GameResult(participants);

        // then
        assertThat(gameResult.getBettingResult(player)).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters() {
        Name playerName = new Name("yeonLog1");
        BettingAmount bettingAmount = new BettingAmount(1000L);

        return Stream.of(
                Arguments.arguments(
                        "사용자 bust, 딜러 blackjack",
                        new Player(playerName, getCards(Number.QUEEN, Number.NINE, Number.JACK), bettingAmount),
                        new Dealer(getCards(Number.QUEEN, Number.ACE)),
                        -1000L
                ),
                Arguments.arguments(
                        "사용자 bust, 딜러 bust",
                        new Player(playerName, getCards(Number.ACE, Number.NINE, Number.JACK), bettingAmount),
                        new Dealer(getCards(Number.TEN, Number.NINE, Number.JACK)),
                        1000L
                ),
                Arguments.arguments(
                        "사용자 bust, 딜러 17",
                        new Player(playerName, getCards(Number.QUEEN, Number.TEN, Number.JACK), bettingAmount),
                        new Dealer(getCards(Number.EIGHT, Number.NINE)),
                        -1000L
                ),
                Arguments.arguments(
                        "사용자 blackjack, 딜러 bust",
                        new Player(playerName, getCards(Number.ACE, Number.TEN), bettingAmount),
                        new Dealer(getCards(Number.TEN, Number.NINE, Number.JACK)),
                        1500L
                ),
                Arguments.arguments(
                        "사용자 blackjack, 딜러 blackjack",
                        new Player(playerName, getCards(Number.ACE, Number.TEN), bettingAmount),
                        new Dealer(getCards(Number.ACE, Number.TEN)),
                        0L
                ),
                Arguments.arguments(
                        "사용자 blackjack, 딜러 17",
                        new Player(playerName, getCards(Number.ACE, Number.TEN), bettingAmount),
                        new Dealer(getCards(Number.EIGHT, Number.NINE)),
                        1500L
                ),
                Arguments.arguments(
                        "사용자 19, 딜러 blackjack",
                        new Player(playerName, getCards(Number.TEN, Number.NINE), bettingAmount),
                        new Dealer(getCards(Number.TEN, Number.ACE)),
                        -1000L
                ),
                Arguments.arguments(
                        "사용자 19, 딜러 bust",
                        new Player(playerName, getCards(Number.TEN, Number.NINE), bettingAmount),
                        new Dealer(getCards(Number.TEN, Number.NINE, Number.JACK)),
                        1000L
                ),
                Arguments.arguments(
                        "사용자 19, 딜러 19",
                        new Player(playerName, getCards(Number.TEN, Number.NINE), bettingAmount),
                        new Dealer(getCards(Number.TEN, Number.NINE)),
                        0L
                ),
                Arguments.arguments(
                        "사용자 19, 딜러 20",
                        new Player(playerName, getCards(Number.TEN, Number.NINE), bettingAmount),
                        new Dealer(getCards(Number.JACK, Number.TEN)),
                        -1000L
                ),
                Arguments.arguments(
                        "사용자 19, 딜러 17",
                        new Player(playerName, getCards(Number.TEN, Number.NINE), bettingAmount),
                        new Dealer(getCards(Number.EIGHT, Number.NINE)),
                        1000L
                )
        );
    }

    @Test
    @DisplayName("딜러 수익")
    void dealer() {
        // given
        BettingAmount bettingAmount = new BettingAmount(1000L);

        List<Player> players = new ArrayList<>();
        players.add(new Player(new Name("a"), getCards(Number.QUEEN, Number.NINE), bettingAmount)); // LOSE
        players.add(new Player(new Name("b"), getCards(Number.QUEEN, Number.JACK), bettingAmount)); // PUSH
        players.add(new Player(new Name("c"), getCards(Number.QUEEN, Number.ACE), bettingAmount));  // WIN

        Dealer dealer = new Dealer(getCards(Number.QUEEN, Number.TEN));

        Participants participants = new Participants(players, dealer);
        GameResult gameResult = new GameResult(participants);

        // then
        assertThat(gameResult.getDealerProfit()).isEqualTo(-500L);
    }
}
