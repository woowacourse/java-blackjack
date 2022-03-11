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
import blackjack.domain.card.strategy.ManualCardStrategy;
import blackjack.domain.result.WinningResult;

class DealerTest {

    private final ManualCardStrategy manualCardStrategy = new ManualCardStrategy();
    private final CardDrawCallback cardDrawCallback = new CardDrawCallback() {
        @Override
        public boolean isContinuable(String participantName) {
            return true;
        }

        @Override
        public void onUpdate(Participant participant) {
        }
    };

    @ParameterizedTest
    @MethodSource("provideForStartWithDrawCardTest")
    @DisplayName("딜러는 카드 2장을 지닌채 게임을 시작한다.")
    void startWithDrawCardTest(final List<Card> expectedCards) {
        manualCardStrategy.initCards(expectedCards);
        Deck deck = Deck.generate(manualCardStrategy);
        final Dealer dealer = Dealer.startWithTwoCards(deck);

        List<String> actualCardNames = dealer.getCardNames();
        List<String> expectedCardNames = expectedCards.stream()
                .map(Card::getCardName)
                .collect(Collectors.toUnmodifiableList())
                .subList(0, 2);
        assertThat(actualCardNames).isEqualTo(expectedCardNames);
    }

    @ParameterizedTest
    @MethodSource("provideForStartWithDrawCardTest")
    @DisplayName("딜러의 카드가 17 미만이라면 추가로 드로우한다.")
    void dealerUnderMinimumTotal(final List<Card> initializedCards, final List<Card> expectedCards) {
        manualCardStrategy.initCards(initializedCards);
        Deck deck = Deck.generate(manualCardStrategy);
        final Dealer dealer = Dealer.startWithTwoCards(deck);
        dealer.continueDraw(deck, cardDrawCallback);

        List<String> actualCardNames = dealer.getCardNames();
        List<String> expectedCardNames = expectedCards.stream()
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

    @ParameterizedTest()
    @MethodSource("provideForDealerLoseByBurst")
    @DisplayName("플레이어의 카드 합이 버스트일 경우 패배한다.")
    void dealerLoseByBurst(List<Card> initializedCards) {
        final ManualCardStrategy manualCardStrategy = new ManualCardStrategy();
        manualCardStrategy.initCards(initializedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Dealer dealer = Dealer.startWithTwoCards(deck);
        dealer.continueDraw(deck, cardDrawCallback);

        assertThat(dealer.judgeWinner(new Player("sun"))).isEqualTo(WinningResult.WIN);
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
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForDealerCalculateWinningResultTest")
    @DisplayName("딜러는 승패를 결정한다.")
    void dealerCalculateWinningResultTest(final List<Card> initializedCards,
                                          final WinningResult expectedWinningResult) {
        final ManualCardStrategy manualCardStrategy = new ManualCardStrategy();
        manualCardStrategy.initCards(initializedCards);
        final Deck deck = Deck.generate(manualCardStrategy);
        final Dealer dealer = Dealer.startWithTwoCards(deck);
        final Player player = new Player("if");
        player.drawCard(deck);
        player.drawCard(deck);
        dealer.continueDraw(deck, cardDrawCallback);

        final WinningResult actualWinningResult = dealer.judgeWinner(player);
        assertThat(actualWinningResult).isEqualTo(expectedWinningResult);
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
                        WinningResult.LOSS
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.FOUR, CardPattern.DIAMOND),
                                new Card(CardNumber.THREE, CardPattern.DIAMOND),
                                new Card(CardNumber.KING, CardPattern.DIAMOND),
                                new Card(CardNumber.JACK, CardPattern.DIAMOND),
                                new Card(CardNumber.QUEEN, CardPattern.DIAMOND)
                        ),
                        WinningResult.WIN
                )
        );
    }
}
