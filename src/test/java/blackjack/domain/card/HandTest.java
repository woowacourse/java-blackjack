package blackjack.domain.card;

import static blackjack.fixture.CardFixture.make;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Hand;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    Hand hand = new Hand();

    @Test
    @DisplayName("하나의 카드를 추가할 수 있다.")
    void canAddCard() {
        Card newCard = new Card(CardShape.HEART, CardValue.EIGHT);

        hand.addCard(newCard);

        List<Card> cardsInHand = hand.getCards();
        assertAll(
                () -> assertThat(cardsInHand).hasSize(1),
                () -> assertThat(cardsInHand.getFirst()).isEqualTo(newCard));
    }

    @Test
    @DisplayName("여러개의 카드를 추가할 수 있다.")
    void canAddCards() {
        List<Card> newCards = List.of(
                new Card(CardShape.HEART, CardValue.EIGHT),
                new Card(CardShape.CLOVER, CardValue.EIGHT));

        hand.addCard(newCards);

        List<Card> cardsInHand = hand.getCards();
        assertAll(
                () -> assertThat(cardsInHand).hasSize(2),
                () -> assertThat(cardsInHand.getFirst()).isEqualTo(newCards.getFirst()),
                () -> assertThat(cardsInHand.getLast()).isEqualTo(newCards.getLast()));
    }

    @ParameterizedTest
    @DisplayName("카드 포인트의 합을 구할 수 있다.")
    @MethodSource()
    void canCalculateTotalPoint(List<Card> cards, int expectedTotalPoint) {
        hand.addCard(cards);

        int actualSum = hand.calculateTotalPoint();

        assertThat(actualSum).isEqualTo(expectedTotalPoint);
    }

    static Stream<Arguments> canCalculateTotalPoint() {
        return Stream.of(
                Arguments.of(List.of(make(CardValue.EIGHT), make(CardValue.EIGHT)), 16),
                Arguments.of(List.of(make(CardValue.ACE), make(CardValue.QUEEN)), 21),
                Arguments.of(List.of(make(CardValue.ACE), make(CardValue.QUEEN), make(CardValue.KING)), 21),
                Arguments.of(List.of(make(CardValue.ACE), make(CardValue.ACE)), 12),
                Arguments.of(List.of(make(CardValue.ACE), make(CardValue.ACE), make(CardValue.ACE)), 13)
        );
    }
}