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
    @DisplayName("첫턴에 딜러는 2장의 카드를 받는다.")
    void construct() {
                new Card(Symbol.TEN, Type.HEART);
        assertThat(Dealer.shuffle(deck)).isNotNull();
    }

    @Test
    @DisplayName("#hit() : should add card without return")
    void hit() {
        //given
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
    void calculateProfit(Result result) {
        Money bettingMoney = mock(Money.class);
        //todo: refac multiply mocking logic
        when(bettingMoney.multiply(anyDouble())).thenReturn(bettingMoney);
        Dealer dealer = Dealer.shuffle(mock(Deck.class));
        //when
        Money profit = dealer.calculateProfit(result, bettingMoney);
        assertThat(profit).isEqualTo(bettingMoney);
        verifyCalculateProfit(result, bettingMoney);

    }

    private void verifyCalculateProfit(Result result, Money bettingMoney) {
        if (result.equals(Result.PLAYER_WIN_WITH_BLACKJACK) || result.equals(Result.PLAYER_WIN_WITHOUT_BLACKJACk)) {
            verify(bettingMoney, times(2)).multiply(anyDouble());
        } else {
            verify(bettingMoney, times(1)).multiply(anyDouble());
        }
    }

    private static Stream<Arguments> getResultsForCalculateProfit() {
        return Stream.of(
                Arguments.of(Result.PLAYER_WIN_WITH_BLACKJACK),
                Arguments.of(Result.PLAYER_WIN_WITHOUT_BLACKJACk),
                Arguments.of(Result.DRAW),
                Arguments.of(Result.DEALER_WIN)
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
