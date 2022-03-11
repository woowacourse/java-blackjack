package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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

class DealerTest {

    private final ManualDeckGenerator manualCardStrategy = new ManualDeckGenerator();

    @DisplayName("딜러는 카드 2장을 지닌채 게임을 시작한다.")
    @ParameterizedTest
    @MethodSource("provideForStartWithDrawCardTest")
    void startWithDrawCardTest(final List<Card> expectedCards) {
        manualCardStrategy.initCards(expectedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Dealer dealer = Dealer.readyToPlay(deck);

        final List<String> actualCardNames = dealer.getCardNames();
        final List<String> expectedCardNames = expectedCards.stream()
                .map(Card::getCardName)
                .collect(Collectors.toUnmodifiableList())
                .subList(0, 2);
        assertThat(actualCardNames).isEqualTo(expectedCardNames);
    }

    @DisplayName("딜러의 카드가 17 미만이라면 추가로 드로우한다.")
    @ParameterizedTest
    @MethodSource("provideForStartWithDrawCardTest")
    void dealerUnderMinimumTotal(final List<Card> initializedCards, final List<Card> expectedCards) {
        manualCardStrategy.initCards(initializedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Dealer dealer = Dealer.readyToPlay(deck);
        dealer.drawCards(deck, new CardDrawCallback() {
            @Override
            public boolean isContinuable(String participantName) {
                return true;
            }

            @Override
            public void onUpdate(String participantName, List<String> cardNames) {

            }
        });

        final List<String> actualCardNames = dealer.getCardNames();
        final List<String> expectedCardNames = expectedCards.stream()
                .map(Card::getCardName)
                .collect(Collectors.toUnmodifiableList());
        assertThat(actualCardNames).isEqualTo(expectedCardNames);
    }

    private static Stream<Arguments> provideForStartWithDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.TWO, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.DIAMOND),
                                new Card(CardNumber.KING, CardPattern.DIAMOND)
                        ),
                        List.of(
                                new Card(CardNumber.TWO, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.DIAMOND),
                                new Card(CardNumber.KING, CardPattern.DIAMOND)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.NINE, CardPattern.SPADE),
                                new Card(CardNumber.KING, CardPattern.HEART),
                                new Card(CardNumber.SEVEN, CardPattern.HEART),
                                new Card(CardNumber.JACK, CardPattern.HEART)
                        ),
                        List.of(
                                new Card(CardNumber.NINE, CardPattern.SPADE),
                                new Card(CardNumber.KING, CardPattern.HEART)
                        )
                )
        );
    }

    @DisplayName("플레이어의 카드 합계가 버스트일 경우, 플레이어는 패배한다.")
    @ParameterizedTest()
    @MethodSource("provideForPlayerLoseByBurst")
    void playerLoseByBurst(final List<Card> initializedCards, final MatchStatus expectedPlayerStatus) {
        manualCardStrategy.initCards(initializedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Dealer dealer = Dealer.readyToPlay(deck);
        final Player player = Player.readyToPlay("sun", deck);
        player.drawCard(deck);

        final MatchStatus actualPlayerStatus = dealer.judgeWinner(player);
        assertThat(actualPlayerStatus).isEqualTo(expectedPlayerStatus);
    }

    private static Stream<Arguments> provideForPlayerLoseByBurst() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.SIX, CardPattern.DIAMOND),
                                new Card(CardNumber.JACK, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.SPADE),
                                new Card(CardNumber.TEN, CardPattern.SPADE)
                        ), MatchStatus.LOSS
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.SIX, CardPattern.DIAMOND),
                                new Card(CardNumber.JACK, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.SPADE),
                                new Card(CardNumber.ACE, CardPattern.SPADE)
                        ), MatchStatus.WIN
                )
        );
    }

    @DisplayName("딜러의 카드 합계가 버스트일 경우, 플레이어가 승리한다.")
    @ParameterizedTest()
    @MethodSource("provideForDealerLoseByBurst")
    void dealerLoseByBurst(final List<Card> initializedCards, final MatchStatus expectedPlayerStatus) {
        manualCardStrategy.initCards(initializedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Dealer dealer = Dealer.readyToPlay(deck);
        dealer.drawCard(deck);
        final Player player = Player.readyToPlay("sun", deck);

        final MatchStatus actualPlayerStatus = dealer.judgeWinner(player);
        assertThat(actualPlayerStatus).isEqualTo(expectedPlayerStatus);
    }

    private static Stream<Arguments> provideForDealerLoseByBurst() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.SIX, CardPattern.DIAMOND),
                                new Card(CardNumber.JACK, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.SPADE),
                                new Card(CardNumber.TEN, CardPattern.SPADE)
                        ), MatchStatus.WIN
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.SIX, CardPattern.DIAMOND),
                                new Card(CardNumber.FOUR, CardPattern.DIAMOND),
                                new Card(CardNumber.EIGHT, CardPattern.SPADE),
                                new Card(CardNumber.TEN, CardPattern.SPADE)
                        ), MatchStatus.LOSS
                )
        );
    }

    @DisplayName("딜러의 카드 합계가 플레이어보다 크거나 같으면 플레이어가 패배한다.")
    @ParameterizedTest
    @MethodSource("provideForDealerCalculateWinningResultTest")
    void dealerJudgeWinnerTest(final List<Card> initializedCards,
                                          final MatchStatus expectedPlayerStatus) {
        manualCardStrategy.initCards(initializedCards);
        final Deck deck = Deck.generate(manualCardStrategy);

        final Dealer dealer = Dealer.readyToPlay(deck);
        final Player player = Player.readyToPlay("if", deck);

        final MatchStatus actualPlayerStatus = dealer.judgeWinner(player);
        assertThat(actualPlayerStatus).isEqualTo(expectedPlayerStatus);
    }

    private static Stream<Arguments> provideForDealerCalculateWinningResultTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.JACK, CardPattern.DIAMOND),
                                new Card(CardNumber.TWO, CardPattern.DIAMOND),
                                new Card(CardNumber.THREE, CardPattern.DIAMOND)
                        ),
                        MatchStatus.LOSS
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.JACK, CardPattern.DIAMOND),
                                new Card(CardNumber.QUEEN, CardPattern.DIAMOND),
                                new Card(CardNumber.TEN, CardPattern.DIAMOND)
                        ),
                        MatchStatus.LOSS
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.FOUR, CardPattern.DIAMOND),
                                new Card(CardNumber.THREE, CardPattern.DIAMOND),
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.JACK, CardPattern.DIAMOND)
                        ),
                        MatchStatus.WIN
                )
        );
    }

}
