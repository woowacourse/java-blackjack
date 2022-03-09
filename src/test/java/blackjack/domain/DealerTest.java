package blackjack.domain;

import blackjack.domain.strategy.ManualCardStrategy;
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
    @DisplayName("딜러의 카드가 17 미만이라면 추가로 드로우한다.")
    void dealerUnderMinimumTotal(final List<Card> initializedCards, final List<Card> expectedCards) {
        manualCardStrategy.initCards(initializedCards);
        Deck deck = Deck.generate(manualCardStrategy);
        final Dealer dealer = Dealer.startWithTwoCards(deck);
        dealer.continueDraw(deck);

        List<Card> actualCards = dealer.getCards();
        assertThat(actualCards).isEqualTo(expectedCards);
    }

    private static Stream<Arguments> provideForStartWithDrawCardTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.ACE),
                                new Card(CardPattern.DIAMOND, CardNumber.EIGHT),
                                new Card(CardPattern.DIAMOND, CardNumber.KING)
                        ),
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.ACE),
                                new Card(CardPattern.DIAMOND, CardNumber.EIGHT),
                                new Card(CardPattern.DIAMOND, CardNumber.KING)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Card(CardPattern.SPADE, CardNumber.NINE),
                                new Card(CardPattern.HEART, CardNumber.KING),
                                new Card(CardPattern.HEART, CardNumber.SEVEN),
                                new Card(CardPattern.HEART, CardNumber.JACK)
                        ),
                        List.of(
                                new Card(CardPattern.SPADE, CardNumber.NINE),
                                new Card(CardPattern.HEART, CardNumber.KING)
                        )
                )
        );
    }
}
