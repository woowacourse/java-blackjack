package blackjack.domain.card;

import blackjack.domain.player.Dealer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CardsTest {

    @Test
    @DisplayName("카드를 추가할 떄 null을 전달하면 예외를 발생한다.")
    void thrownExceptionWhenGivenNull() {
        Cards cards = new Cards(new Deck().makeDistributeCard());
        assertThatThrownBy(() -> cards.addCard(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바른 카드를 입력해주세요.");
    }

    @Test
    @DisplayName("카드를 추가하면 관리하는 카드 개수가 늘어난다.")
    void addCard() {
        Cards cards = new Cards(List.of(
                new Card(Type.SPADE, Score.ACE),
                new Card(Type.DIAMOND, Score.TWO)
        ));
        cards.addCard(new Card(Type.CLOVER, Score.THREE));

        assertThat(cards.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("cards의 점수 합을 구한다.")
    void sumCardScore() {
        Cards cards = new Cards(List.of(
                new Card(Type.SPADE, Score.ACE),
                new Card(Type.DIAMOND, Score.TWO)
        ));
        cards.addCard(new Card(Type.CLOVER, Score.THREE));

        assertThat(cards.calculateFinalScore()).isEqualTo(16);
    }

    @ParameterizedTest
    @MethodSource("getCards")
    @DisplayName("cards의 배분된 초기 카드는 2장이어야 한다.")
    void thrownExceptionWhenGivenDistributeCard(List<Card> cards) {
        assertThatThrownBy(() -> new Cards(cards)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못 배분된 카드입니다.");
    }

    private static Stream<List<Card>> getCards() {
        return Stream.of(
                null,
                Collections.emptyList(),
                List.of(
                        new Card(Type.SPADE, Score.ACE)
                ),
                List.of(
                        new Card(Type.SPADE, Score.ACE),
                        new Card(Type.DIAMOND, Score.TWO),
                        new Card(Type.CLOVER, Score.THREE)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("dealerList")
    void calculateParticipantScore(List<Card> receivedCards, Card card, int score) {
        Cards cards = new Cards(receivedCards);
        cards.addCard(card);

        Assertions.assertThat(cards.calculateFinalScore()).isEqualTo(score);
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
                ), new Card(Type.SPADE, Score.EIGHT), 25),
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.ACE),
                        new Card(Type.HEART, Score.ACE)
                ), new Card(Type.HEART, Score.NINE), 21),
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.ACE),
                        new Card(Type.HEART, Score.JACK)
                ), new Card(Type.HEART, Score.ACE), 12),
                Arguments.of(List.of(
                        new Card(Type.SPADE, Score.TWO),
                        new Card(Type.HEART, Score.EIGHT)
                ), new Card(Type.HEART, Score.ACE), 21)
        );
    }
}
