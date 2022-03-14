package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Deck;
import blackjack.domain.result.PlayerResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @ParameterizedTest
    @MethodSource("provideForStartWithDrawCardTest")
    @DisplayName("딜러는 카드 2장을 지닌채 게임을 시작한다.")
    void startWithDrawCardTest(final List<Card> initializedCards) {
        Deck deck = new Deck(initializedCards);
        final Dealer dealer = Dealer.startWithTwoCards(deck);

        List<Card> cards = dealer.getCards();
        assertThat(cards).isEqualTo(initializedCards.subList(0, 2));
    }

    @ParameterizedTest
    @MethodSource("provideForStartWithDrawCardTest")
    @DisplayName("딜러의 카드 점수에 따라 카드 뽑기 가능 여부를 반환한다.")
    void dealerUnderMinimumTotal(final List<Card> initializedCards, final boolean drawable) {
        Deck deck = new Deck(initializedCards);
        final Dealer dealer = Dealer.startWithTwoCards(deck);

        assertThat(dealer.shouldDraw()).isEqualTo(drawable);
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
    @CsvSource(value = "KING,SIX,JACK")
    @DisplayName("딜러의 카드 합이 버스트일 경우 패배한다.")
    void dealerLoseByBurst(CardNumber cardNumber1, CardNumber cardNumber2, CardNumber cardNumber3) {

        final Deck deck = new Deck(List.of(
                new Card(CardPattern.DIAMOND, cardNumber1),
                new Card(CardPattern.DIAMOND, cardNumber2),
                new Card(CardPattern.DIAMOND, cardNumber3)));

        final Dealer dealer = Dealer.startWithTwoCards(deck);

        dealer.drawCard(deck);


        assertThat(dealer.judgeWinner(new Player("sun"))).isEqualTo(PlayerResult.WIN);
    }

    @ParameterizedTest
    @CsvSource(value = {"KING,FIVE,LOSS", "KING,ACE,WIN"})
    @DisplayName("딜러는 승패를 결정한다.")
    void dealerCalculateWinningResultTest(final CardNumber cardNumber1,
                                          final CardNumber cardNumber2,
                                          final PlayerResult result) {
        final Deck dealerDeck = new Deck(List.of(
                new Card(CardPattern.DIAMOND, CardNumber.JACK),
                new Card(CardPattern.DIAMOND, CardNumber.QUEEN)));

        Deck playerDeck = new Deck(List.of(
                new Card(CardPattern.DIAMOND, cardNumber1),
                new Card(CardPattern.DIAMOND, cardNumber2)
        ));

        final Dealer dealer = Dealer.startWithTwoCards(dealerDeck);
        Players players = Players.startWithTwoCards(Collections.singletonList("sun"), playerDeck);

        final PlayerResult actualPlayerResult = dealer.judgeWinner(players.getStatuses().get(0));
        assertThat(actualPlayerResult).isEqualTo(result);
    }
}
