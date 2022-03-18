package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestUtil.BETTING_1000;
import static utils.TestUtil.CLOVER_ACE;
import static utils.TestUtil.CLOVER_EIGHT;
import static utils.TestUtil.CLOVER_JACK;
import static utils.TestUtil.CLOVER_NINE;
import static utils.TestUtil.CLOVER_QUEEN;
import static utils.TestUtil.CLOVER_TEN;
import static utils.TestUtil.CLOVER_TWO;
import static utils.TestUtil.getCards;

import blackjack.domain.card.Cards;
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
    private static final Cards BLACKJACK_CARDS = getCards(CLOVER_ACE, CLOVER_JACK);
    private static final Cards BUST_CARDS = getCards(CLOVER_TWO, CLOVER_JACK, CLOVER_QUEEN);

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
        return Stream.of(
                Arguments.arguments(
                        "사용자 bust, 딜러 blackjack",
                        new Player(playerName, BUST_CARDS, BETTING_1000),
                        new Dealer(BLACKJACK_CARDS),
                        -1000L
                ),
                Arguments.arguments(
                        "사용자 bust, 딜러 bust",
                        new Player(playerName, BUST_CARDS, BETTING_1000),
                        new Dealer(BUST_CARDS),
                        0L
                ),
                Arguments.arguments(
                        "사용자 bust, 딜러 17",
                        new Player(playerName, BUST_CARDS, BETTING_1000),
                        new Dealer(getCards(CLOVER_EIGHT, CLOVER_NINE)),
                        -1000L
                ),
                Arguments.arguments(
                        "사용자 blackjack, 딜러 bust",
                        new Player(playerName, BLACKJACK_CARDS, BETTING_1000),
                        new Dealer(BUST_CARDS),
                        1500L
                ),
                Arguments.arguments(
                        "사용자 blackjack, 딜러 blackjack",
                        new Player(playerName, BLACKJACK_CARDS, BETTING_1000),
                        new Dealer(BLACKJACK_CARDS),
                        0L
                ),
                Arguments.arguments(
                        "사용자 blackjack, 딜러 17",
                        new Player(playerName, BLACKJACK_CARDS, BETTING_1000),
                        new Dealer(getCards(CLOVER_EIGHT, CLOVER_NINE)),
                        1500L
                ),
                Arguments.arguments(
                        "사용자 19, 딜러 blackjack",
                        new Player(playerName, getCards(CLOVER_TEN, CLOVER_NINE), BETTING_1000),
                        new Dealer(BLACKJACK_CARDS),
                        -1000L
                ),
                Arguments.arguments(
                        "사용자 19, 딜러 bust",
                        new Player(playerName, getCards(CLOVER_TEN, CLOVER_NINE), BETTING_1000),
                        new Dealer(BUST_CARDS),
                        1000L
                ),
                Arguments.arguments(
                        "사용자 19, 딜러 19",
                        new Player(playerName, getCards(CLOVER_TEN, CLOVER_NINE), BETTING_1000),
                        new Dealer(getCards(CLOVER_TEN, CLOVER_NINE)),
                        0L
                ),
                Arguments.arguments(
                        "사용자 19, 딜러 20",
                        new Player(playerName, getCards(CLOVER_TEN, CLOVER_NINE), BETTING_1000),
                        new Dealer(getCards(CLOVER_JACK, CLOVER_TEN)),
                        -1000L
                ),
                Arguments.arguments(
                        "사용자 19, 딜러 17",
                        new Player(playerName, getCards(CLOVER_TEN, CLOVER_NINE), BETTING_1000),
                        new Dealer(getCards(CLOVER_EIGHT, CLOVER_NINE)),
                        1000L
                )
        );
    }

    @Test
    @DisplayName("딜러 수익")
    void dealer() {
        // given
        Cards cards = getCards(CLOVER_QUEEN, CLOVER_JACK);

        List<Player> players = new ArrayList<>();
        players.add(new Player(new Name("a"), BUST_CARDS, BETTING_1000));          // LOSE
        players.add(new Player(new Name("c"), BLACKJACK_CARDS, BETTING_1000));     // WIN
        players.add(new Player(new Name("b"), cards, BETTING_1000));               // PUSH

        Dealer dealer = new Dealer(cards);

        Participants participants = new Participants(players, dealer);
        GameResult gameResult = new GameResult(participants);

        // then
        assertThat(gameResult.getDealerProfit()).isEqualTo(-500L);
    }
}
