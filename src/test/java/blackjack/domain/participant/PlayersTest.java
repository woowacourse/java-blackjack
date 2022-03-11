package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.ManualDeckGenerator;
import blackjack.domain.result.MatchStatus;

public class PlayersTest {

    private final ManualDeckGenerator manualCardStrategy = new ManualDeckGenerator();

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("provideForPlayerNamesDuplicatedExceptionTest")
    @DisplayName("플레이어명 중복 시 예외 발생")
    void playerNamesDuplicatedExceptionTest(final List<String> playerNames, final List<Card> initializedCards) {
        manualCardStrategy.initCards(initializedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        assertThatThrownBy(() -> Players.readyToPlay(playerNames, deck))
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
    void startWithDrawCardTest(final List<String> playerNames, final List<Card> expectedCards) {
        manualCardStrategy.initCards(expectedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Players players = Players.readyToPlay(playerNames, deck);
        final List<Player> actualPlayers = players.getPlayers();

        final List<String> actualCardNames = actualPlayers.stream()
                .map(Participant::getCardNames)
                .flatMap(List::stream)
                .collect(Collectors.toUnmodifiableList());
        final List<String> expectedCardNames = expectedCards.stream()
                .map(Card::getCardName)
                .collect(Collectors.toUnmodifiableList());
        assertThat(actualCardNames).isEqualTo(expectedCardNames);
    }

    private static Stream<Arguments> provideForStartWithDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of("sun", "if"),
                        List.of(
                                new Card(CardNumber.ACE, CardPattern.DIAMOND),
                                new Card(CardNumber.TWO, CardPattern.DIAMOND),
                                new Card(CardNumber.THREE, CardPattern.DIAMOND),
                                new Card(CardNumber.FOUR, CardPattern.DIAMOND)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForCompareCardTotalTest")
    @DisplayName("각 플레이어는 딜러와의 승패를 계산한다.")
    void compareCardSum(final List<String> names,
                        final List<Card> initializedCards,
                        final Map<String, MatchStatus> expectedWinningStatuses) {
        manualCardStrategy.initCards(initializedCards);
        final Deck deck = Deck.generate(manualCardStrategy);

        final Dealer dealer = Dealer.readyToPlay(deck);
        final Players players = Players.readyToPlay(names, deck);

        final Map<String, MatchStatus> actualWinningStatuses = players.judgeWinners(dealer).getResultOfPlayers();
        assertThat(actualWinningStatuses).isEqualTo(expectedWinningStatuses);
    }

    private static Stream<Arguments> provideForCompareCardTotalTest() {
        return Stream.of(
                Arguments.of(
                        List.of("sun"),
                        List.of(
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.TEN, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.DIAMOND),
                                new Card(CardNumber.NINE, CardPattern.DIAMOND)
                        ),
                        Map.of("sun", MatchStatus.LOSS)
                ),
                Arguments.of(
                        List.of("sun", "if"),
                        List.of(
                                new Card(CardNumber.NINE, CardPattern.SPADE),
                                new Card(CardNumber.EIGHT, CardPattern.HEART),
                                new Card(CardNumber.TEN, CardPattern.SPADE),
                                new Card(CardNumber.EIGHT, CardPattern.SPADE),
                                new Card(CardNumber.TEN, CardPattern.HEART),
                                new Card(CardNumber.TWO, CardPattern.SPADE)
                        ),
                        Map.of(
                                "sun", MatchStatus.WIN,
                                "if", MatchStatus.LOSS
                        )
                )
        );
    }

}
