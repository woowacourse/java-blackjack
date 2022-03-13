package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Deck;
import blackjack.domain.card.strategy.ManualCardStrategy;
import blackjack.domain.result.PlayerResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    private final ManualCardStrategy manualCardStrategy = new ManualCardStrategy();

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("provideForPlayerNamesDuplicatedExceptionTest")
    @DisplayName("플레이어명 중복 시 예외 발생")
    void playerNamesDuplicatedExceptionTest(final List<String> playerNames, final List<Card> initializedCards) {
        manualCardStrategy.initCards(initializedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        assertThatThrownBy(() -> Players.startWithTwoCards(playerNames, deck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어명은 중복될 수 없습니다.");
    }

    private static Stream<Arguments> provideForPlayerNamesDuplicatedExceptionTest() {
        return Stream.of(
                Arguments.of(List.of("pobi", "pobi"), Collections.emptyList()),
                Arguments.of(List.of("pobi", "sun", "pobi"), Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("provideForStartWithDrawCardTest")
    @DisplayName("각 플레이어는 카드 2장을 지닌채 게임을 시작한다.")
    void startWithDrawCardTest(final List<String> playerNames, final List<Card> initializedCards) {
        manualCardStrategy.initCards(initializedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Players players = Players.startWithTwoCards(playerNames, deck);
        final List<Player> actualPlayers = players.getStatuses();

        final List<Card> actualCards = new ArrayList<>();
        for (Player player : actualPlayers) {
            actualCards.addAll(player.openAllCards());
        }
        assertThat(actualCards).isEqualTo(initializedCards);
    }

    private static Stream<Arguments> provideForStartWithDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of("sun", "if"),
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.ACE),
                                new Card(CardPattern.DIAMOND, CardNumber.TWO),
                                new Card(CardPattern.DIAMOND, CardNumber.THREE),
                                new Card(CardPattern.DIAMOND, CardNumber.FOUR)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForCompareCardTotalTest")
    @DisplayName("각 플레이어는 딜러와의 승패를 계산한다.")
    void compareCardSum(final List<String> names,
                        final List<Card> initializedCards,
                        final Map<String, PlayerResult> expectedWinningResults) {
        manualCardStrategy.initCards(initializedCards);
        final Deck deck = Deck.generate(manualCardStrategy);

        final Dealer dealer = Dealer.startWithTwoCards(deck);
        final Players players = Players.startWithTwoCards(names, deck);

        final Map<String, PlayerResult> actualWinningResults = players.judgeWinners(dealer).getPlayerResult();
        assertThat(actualWinningResults).isEqualTo(expectedWinningResults);
    }

    private static Stream<Arguments> provideForCompareCardTotalTest() {
        return Stream.of(
                Arguments.of(
                        List.of("sun"),
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.KING),
                                new Card(CardPattern.DIAMOND, CardNumber.TEN),
                                new Card(CardPattern.DIAMOND, CardNumber.EIGHT),
                                new Card(CardPattern.DIAMOND, CardNumber.NINE)
                        ),
                        Map.of("sun", PlayerResult.LOSS)
                ),
                Arguments.of(
                        List.of("sun", "if"),
                        List.of(
                                new Card(CardPattern.SPADE, CardNumber.NINE),
                                new Card(CardPattern.HEART, CardNumber.EIGHT),
                                new Card(CardPattern.SPADE, CardNumber.TEN),
                                new Card(CardPattern.SPADE, CardNumber.EIGHT),
                                new Card(CardPattern.HEART, CardNumber.TEN),
                                new Card(CardPattern.SPADE, CardNumber.TWO)
                        ),
                        Map.of(
                                "sun", PlayerResult.WIN,
                                "if", PlayerResult.LOSS
                        )
                )
        );
    }
}
