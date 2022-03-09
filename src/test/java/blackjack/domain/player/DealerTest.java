package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("딜러는 시작시 카드를 2장 받는다.")
    void checkParticipantCardSize() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer(deck.initDistributeCard());
        assertThat(dealer.getCards().size()).isEqualTo(2);
    }

    @ParameterizedTest
    @MethodSource("cardListAndAcceptable")
    @DisplayName("딜러는 자신의 점수가 16이하인지 확인한다.")
    void addParticipantCard(List<Card> cards, boolean acceptable) {
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.acceptableCard()).isEqualTo(acceptable);
    }

    private static Stream<Arguments> cardListAndAcceptable() {
        return Stream.of(
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.EIGHT),
                        new Card(Type.HEART, Score.EIGHT)
                ), true),
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.EIGHT),
                        new Card(Type.HEART, Score.NINE)
                ), false),
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.ACE),
                        new Card(Type.HEART, Score.FIVE)
                ), true),
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.ACE),
                        new Card(Type.HEART, Score.SIX)
                ), false)
        );
    }

    @ParameterizedTest
    @MethodSource("dealerList")
    void calculateParticipantScore(List<Card> cards, Card addCard, int score) {
        Dealer dealer = new Dealer(cards);

        if (dealer.acceptableCard()){
            dealer.addCard(addCard);
        }

        assertThat(dealer.calculateScore()).isEqualTo(score);
    }

    private static Stream<Arguments> dealerList() {
        return Stream.of(
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.EIGHT),
                        new Card(Type.HEART, Score.EIGHT)
                ), new Card(Type.SPADE, Score.EIGHT), 24),
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.EIGHT),
                        new Card(Type.HEART, Score.NINE)
                ), new Card(Type.SPADE, Score.EIGHT), 17),
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.ACE),
                        new Card(Type.HEART, Score.ACE)
                ), new Card(Type.HEART, Score.NINE), 21),
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.ACE),
                        new Card(Type.HEART, Score.JACK)
                ), new Card(Type.HEART, Score.ACE), 21),
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.TWO),
                        new Card(Type.HEART, Score.EIGHT)
                ), new Card(Type.HEART, Score.ACE), 21)
        );
    }
}