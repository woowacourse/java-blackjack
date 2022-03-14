package blackjack.domain.player;

import blackjack.domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("딜러는 시작시 카드를 2장 받는다.")
    void checkParticipantCardSize() {
        Deck deck = new Deck(new RandomCardGenerator());
        Dealer dealer = new Dealer(List.of(deck.draw(), deck.draw()));
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
}
