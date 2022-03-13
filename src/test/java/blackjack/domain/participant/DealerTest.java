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

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    private final ManualCardStrategy manualCardStrategy = new ManualCardStrategy();

    @ParameterizedTest
    @MethodSource("provideForStartWithDrawCardTest")
    @DisplayName("딜러는 카드 2장을 지닌채 게임을 시작한다.")
    void startWithDrawCardTest(final List<Card> initializedCards) {
        manualCardStrategy.initCards(initializedCards);
        Deck deck = Deck.generate(manualCardStrategy);
        final Dealer dealer = Dealer.startWithTwoCards(deck);

        List<Card> cards = dealer.getCards();
        assertThat(cards).isEqualTo(initializedCards.subList(0, 2));
    }

    @ParameterizedTest
    @MethodSource("provideForStartWithDrawCardTest")
    @DisplayName("딜러의 카드 점수에 따라 카드 뽑기 가능 여부를 반환한다.")
    void dealerUnderMinimumTotal(final List<Card> initializedCards, final boolean canDraw) {
        Deck deck = new Deck(initializedCards);
        final Dealer dealer = Dealer.startWithTwoCards(deck);

        assertThat(dealer.shouldDraw()).isEqualTo(canDraw);
    }

    private static Stream<Arguments> provideForStartWithDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.TWO),
                                new Card(CardPattern.DIAMOND, CardNumber.EIGHT),
                                new Card(CardPattern.DIAMOND, CardNumber.KING)
                        ), true
                ),
                Arguments.of(
                        List.of(
                                new Card(CardPattern.SPADE, CardNumber.NINE),
                                new Card(CardPattern.HEART, CardNumber.KING)
                        ), false
                )
        );
    }

    @ParameterizedTest()
    @MethodSource("provideForDealerLoseByBurst")
    @DisplayName("딜러의 카드 합이 버스트일 경우 패배한다.")
    void dealerLoseByBurst(List<Card> initializedCards) {
        final Deck deck = new Deck(initializedCards);
        final Dealer dealer = Dealer.startWithTwoCards(deck);

        while (dealer.shouldDraw()) {
            dealer.drawCard(deck);
        }

        assertThat(dealer.judgeWinner(new Player("sun"))).isEqualTo(PlayerResult.WIN);
    }

    private static Stream<Arguments> provideForDealerLoseByBurst() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.KING),
                                new Card(CardPattern.DIAMOND, CardNumber.SIX),
                                new Card(CardPattern.DIAMOND, CardNumber.JACK),
                                new Card(CardPattern.SPADE, CardNumber.EIGHT),
                                new Card(CardPattern.SPADE, CardNumber.TEN)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForDealerCalculateWinningResultTest")
    @DisplayName("딜러는 승패를 결정한다.")
    void dealerCalculateWinningResultTest(final List<Card> dealerCards,
                                          final List<Card> playersCards,
                                          final PlayerResult expectedPlayerResult) {
        final Deck dealerDeck = new Deck(dealerCards);
        final Deck playersDeck = new Deck(playersCards);

        final Dealer dealer = Dealer.startWithTwoCards(dealerDeck);
        final Player player = new Player("if");
        player.drawCard(playersDeck);
        player.drawCard(playersDeck);

        final PlayerResult actualPlayerResult = dealer.judgeWinner(player);
        assertThat(actualPlayerResult).isEqualTo(expectedPlayerResult);
    }

    private static Stream<Arguments> provideForDealerCalculateWinningResultTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.KING),
                                new Card(CardPattern.DIAMOND, CardNumber.JACK)
                        ),
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.TWO),
                                new Card(CardPattern.DIAMOND, CardNumber.THREE)
                        ),
                        PlayerResult.LOSS
                ),
                Arguments.of(
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.FOUR),
                                new Card(CardPattern.DIAMOND, CardNumber.THREE),
                                new Card(CardPattern.DIAMOND, CardNumber.KING)
                        ),
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.JACK),
                                new Card(CardPattern.DIAMOND, CardNumber.QUEEN)
                        ),
                        PlayerResult.WIN
                )
        );
    }
}
