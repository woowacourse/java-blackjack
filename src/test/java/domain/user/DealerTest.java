package domain.user;

import domain.card.*;
import domain.result.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {


    @Test
    @DisplayName("#shuffle() : should return Dealer")
    void construct() {
        Deck deck = createDeckforTest(2);
        assertThat(Dealer.shuffle(deck)).isNotNull();
    }

    @Test
    @DisplayName("#hit() : should add card without return")
    void hit() {
        //given
        Deck deck = createDeckforTest(2);
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
        Deck deck = createDeckforTest(4);
        Dealer dealer = Dealer.shuffle(deck);
        int hitSize = 2;
        int defaultSizeOfCards = dealer.countCards();

        //when
        dealer.confirmCards(hitSize);

        //then
        assertThat(dealer.countCards()).isEqualTo(defaultSizeOfCards + hitSize);
    }

    @ParameterizedTest
    @MethodSource({"getResultsForCalculateProfit"})
    void calculateProfit(Result result, int moneyValue, int expectedProfitValue) {
        //given
        Money bettingMoney = Money.of(moneyValue);
        Deck deck = createDeckforTest(2);
        Dealer dealer = Dealer.shuffle(deck);
        //when
        Profit actualProfit = dealer.calculateProfit(result, bettingMoney);
        //then
        assertThat(actualProfit).isEqualTo(new Profit(expectedProfitValue));
    }

    private static Stream<Arguments> getResultsForCalculateProfit() {
        return Stream.of(
                Arguments.of(Result.PLAYER_WIN_WITH_BLACKJACK, 1000, -1500),
                Arguments.of(Result.PLAYER_WIN_WITHOUT_BLACKJACk, 1000, -1000),
                Arguments.of(Result.DRAW, 1000, 0),
                Arguments.of(Result.DEALER_WIN, 1000, 1000)
        );
    }


    private Deck createDeckforTest(int countOfPop) {
        final Integer[] countOfPops = new Integer[countOfPop];

        class TestDeck implements Deck {

            @Override
            public Deck shuffle() {
                return this;
            }

            @Override
            public Card pop() {
                if (countOfPops[0] == null) {
                    countOfPops[0] = 1;
                    return new Card(Symbol.QUEEN, Type.SPADE);
                } else if(countOfPops[1] == null) {
                    return new Card(Symbol.QUEEN, Type.CLOVER);
                } else if(countOfPops[2] == null) {
                    new Card(Symbol.QUEEN, Type.HEART);
                } else if (countOfPops[3] == null) {
                    return new Card(Symbol.QUEEN, Type.DIAMOND);
                }
                return new Card(Symbol.KING, Type.SPADE);
            }
        }

        return new TestDeck();
    }
}
