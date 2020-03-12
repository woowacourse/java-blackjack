package user;

import card.Card;
import card.Deck;
import card.Symbol;
import card.Type;
import org.junit.jupiter.api.BeforeAll;
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

public class DealerTest {
    private static Deck deck;

    @BeforeAll
    static void setup() {
        deck = new Deck();
    }

    @DisplayName("생성했을 때, 두 장의 카드를 보유하고 있는지 확인")
    @Test
    void constructor_InitializeDealer_HandsSizeIsTwo() {
        Dealer dealer = new Dealer(deck);
        assertThat(dealer.handSize()).isEqualTo(2);
    }

    @DisplayName("패의 숫자가 16 초과일 때, 카드를 뽑지 않음을 확인")
    @ParameterizedTest
    @MethodSource("over16Data")
    void needMoreCard_ScoreOver16_None(List<Card> hands) {
        Hands hand = new Hands(hands);
        Dealer dealer = new Dealer(hand);

        dealer.hit(deck);

        assertThat(dealer.handSize()).isEqualTo(2);
    }

    static Stream<Arguments> over16Data() {
        Card ace = new Card(Symbol.ACE, Type.CLUB);
        Card six = new Card(Symbol.SIX, Type.DIAMOND);
        Card eight = new Card(Symbol.EIGHT, Type.SPADE);
        Card king = new Card(Symbol.KING, Type.HEART);

        return Stream.of(
                Arguments.of(new ArrayList<>(Arrays.asList(ace, six))),
                Arguments.of(new ArrayList<>(Arrays.asList(ace, eight))),
                Arguments.of(new ArrayList<>(Arrays.asList(ace, king))),
                Arguments.of(new ArrayList<>(Arrays.asList(eight, king)))
        );
    }

    @DisplayName("패의 숫자가 17 미만일 때, 카드를 한 장 더 받는지 확인")
    @ParameterizedTest
    @MethodSource("under17Data")
    void needMoreCard_ScoreUnder17_Draw(List<Card> hands) {
        Hands hand = new Hands(hands);
        Dealer dealer = new Dealer(hand);

        dealer.hit(deck);

        assertThat(dealer.handSize()).isEqualTo(3);
    }

    static Stream<Arguments> under17Data() {
        Card ace = new Card(Symbol.ACE, Type.CLUB);
        Card four = new Card(Symbol.FOUR, Type.DIAMOND);
        Card five = new Card(Symbol.FIVE, Type.SPADE);
        Card seven = new Card(Symbol.SEVEN, Type.HEART);

        return Stream.of(
                Arguments.of(new ArrayList<>(Arrays.asList(ace, four))),
                Arguments.of(new ArrayList<>(Arrays.asList(ace, five))),
                Arguments.of(new ArrayList<>(Arrays.asList(four, five))),
                Arguments.of(new ArrayList<>(Arrays.asList(four, seven)))
        );
    }
}
