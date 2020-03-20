package domain.user;

import domain.card.*;
import domain.result.Result;
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
import static org.mockito.Mockito.*;

class DealerTest {

    Deck deck = mock(Deck.class);

    @Test
    @DisplayName("#shuffle() : should return Dealer")
    void construct() {
        when(deck.shuffle()).thenReturn(deck);
        new Card(Symbol.TEN, Type.HEART);
        assertThat(Dealer.shuffle(deck)).isNotNull();
    }

    @Test
    @DisplayName("#hit() : should add card without return")
    void hit() {
        //given
        when(deck.shuffle()).thenReturn(deck);
        when(deck.pop())
                .thenReturn(new Card(Symbol.QUEEN, Type.SPADE))
                .thenReturn(new Card(Symbol.QUEEN, Type.CLOVER));
        Dealer dealer = Dealer.shuffle(deck);
        Card card = new Card(Symbol.QUEEN, Type.SPADE);

        //when
        dealer.hit(card);

        //then
        assertThat(dealer.countCards()).isEqualTo(3);
    }

    @Test
    @DisplayName("#confirmCards : should add cards for given times without return")
    void confirmCards() {
        //given
        when(deck.shuffle()).thenReturn(deck);
        when(deck.pop())
                .thenReturn(new Card(Symbol.QUEEN, Type.SPADE))
                .thenReturn(new Card(Symbol.KING, Type.SPADE));
        Dealer dealer = Dealer.shuffle(deck);
        List<Card> cards = setUpDeck();
        int hitSize = cards.size();
        int defaultSizeOfCards = dealer.countCards();

        //when
        dealer.confirmCards(hitSize);

        //then
        assertThat(dealer.countCards()).isEqualTo(defaultSizeOfCards + hitSize);
    }

    @ParameterizedTest
    @MethodSource({"getResultsForCalculateProfit"})
    void calculateProfit(Result result, int rate) {
        //given
        Money bettingMoney = mock(Money.class);
        double valueOfBettingMoney = 10;
        when(bettingMoney.multiply(anyDouble())).thenReturn(bettingMoney);
        when(bettingMoney.getValue()).thenReturn(valueOfBettingMoney);
        Profit expectedProfit = mock(Profit.class);
        when(expectedProfit.multiply((int) valueOfBettingMoney)).thenReturn(expectedProfit);
        Deck deck = mock(Deck.class);
        when(deck.shuffle()).thenReturn(deck);
        Dealer dealer = Dealer.shuffle(deck);
        //when
        Profit actualProfit = dealer.calculateProfit(result, bettingMoney);
        //then
        assertThat(actualProfit).isEqualTo(new Profit(((int) valueOfBettingMoney) * rate));
        verify(bettingMoney).multiply(anyDouble());

    }

    private static Stream<Arguments> getResultsForCalculateProfit() {
        return Stream.of(
                Arguments.of(Result.PLAYER_WIN_WITH_BLACKJACK, -1),
                Arguments.of(Result.PLAYER_WIN_WITHOUT_BLACKJACk, -1),
                Arguments.of(Result.DRAW, 1),
                Arguments.of(Result.DEALER_WIN, 1)
        );
    }

    private List<Card> setUpDeck() {
        List<Card> cards = Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.QUEEN, Type.DIAMOND), new Card(Symbol.QUEEN, Type.CLOVER));
        for (Card card : cards) {
            when(deck.pop()).thenReturn(card);
        }
        return cards;
    }
}
