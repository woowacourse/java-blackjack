package model.participant;

import java.util.List;
import java.util.stream.Stream;

import card.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import participant.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static util.TestCardDistributor.divideCardToDealer;

class DealerTest {

    private final CardDeck deck = new CardDeck(new ShuffledDeckGenerator().generateDeck());

    @Test
    @DisplayName("카드 추가 기능이 잘 작동하는 지")
    void addCardsSuccess() {

        // given
        final Card card = deck.pickCard();

        // when
        Dealer dealer = new Dealer();
        dealer.addCard(card);

        // then
        assertThat(dealer.getCards()).contains(card);
    }

    @Test
    @DisplayName("딜러의 핸즈에 있는 카드 랭크의 합을 잘 구하는 지")
    void sum() {
        // given
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, NormalRank.FIVE),
                new Card(Suit.CLUBS, NormalRank.FOUR)
        );

        int expected = cards.stream()
                .mapToInt(card -> card.getRank().getScore())
                .sum();

        Dealer dealer = new Dealer();
        divideCardToDealer(cards,dealer);

        // when
        int sum = dealer.calculateScore().getValue();

        // then
        assertThat(sum).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러 카드 합산이 조건에 부합하는 지 판별: true")
    void isNotUpTrue() {

        // given
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, NormalRank.JACK),
                new Card(Suit.CLUBS, NormalRank.FOUR)
        );

        Dealer dealer = new Dealer();
        divideCardToDealer(cards, dealer);

        // when
        // then
        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("딜러 카드 합산이 조건에 부합하는 지 판별: false")
    void isNotUpFalse() {

        // given
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, NormalRank.JACK),
                new Card(Suit.CLUBS, NormalRank.KING)
        );

        Dealer dealer = new Dealer();
        divideCardToDealer(cards, dealer);

        // when
        // then
        assertThat(dealer.canHit()).isFalse();
    }

    @DisplayName("딜러가 블랙잭인 경우: true")
    @Test
    void isBlackJackTest() {
        //given
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, AceRank.SOFT_ACE),
                new Card(Suit.CLUBS, NormalRank.KING)
        );

        Dealer dealer = new Dealer();
        divideCardToDealer(cards, dealer);

        //when
        //then
        Assertions.assertThat(dealer.isBlackJack()).isTrue();
    }

    @DisplayName("딜러가 블랙잭이 아닌 경우: false")
    @ParameterizedTest
    @MethodSource("makeNoneBlackJackDeck")
    void isNotBlackJackTest(List<Card> cards) {
        //given
        Dealer dealer = new Dealer();
        divideCardToDealer(cards, dealer);

        //when
        //then
        Assertions.assertThat(dealer.isBlackJack()).isFalse();
    }

    private static Stream<Arguments> makeNoneBlackJackDeck() {
        return Stream.of(
                Arguments.arguments(
                        List.of(
                                new Card(Suit.HEARTS, AceRank.HARD_ACE),
                                new Card(Suit.CLUBS, NormalRank.KING)
                        )
                ),
                Arguments.arguments(
                        List.of(
                                new Card(Suit.HEARTS, NormalRank.JACK),
                                new Card(Suit.CLUBS, NormalRank.FIVE)
                        )
                )
        );
    }

}
