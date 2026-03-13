package domain.participant;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HandCardsTest {
    @Test
    @DisplayName("카드를 한장 받는다.")
    void hitCardTest() {
        // Given
        HandCards handCards = new HandCards();

        // When
        handCards.receiveHitCard(new Card(CardNumber.ACE, CardShape.CLUB));
        int size = handCards.getCards().size();

        // Then
        assertThat(size).isEqualTo(1);
    }

    @Test
    @DisplayName("카드 점수를 계산한다.")
    void calculateScoreTest() {
        // Given
        List<Card> cards = List.of(
                new Card(CardNumber.EIGHT, CardShape.CLUB),
                new Card(CardNumber.FOUR, CardShape.CLUB)
        );

        HandCards handCards = new HandCards(cards);
        // When
        int score = handCards.calculateScore();

        // Then
        assertThat(score).isEqualTo(12);
    }


    @Test
    @DisplayName("Ace에 대한 점수를 처리한다. 만약 10을 더해서 21을 넘는다면 1로 계산한다.")
    void judgeAceAsOneTest() {
        // Given
        List<Card> cards = List.of(
                new Card(CardNumber.JACK, CardShape.CLUB),
                new Card(CardNumber.FOUR, CardShape.CLUB),
                new Card(CardNumber.ACE, CardShape.CLUB)
        );

        HandCards handCards = new HandCards(cards);

        // When
        int score = handCards.calculateScore();

        // Then
        assertThat(score).isEqualTo(15);
    }

    @Test
    @DisplayName("Ace에 대한 점수를 처리한다. 만약 10을 더해서 21을 넘는다면 1로 계산한다.")
    void judgeAceAsElevenTest() {
        // Given
        List<Card> cards = List.of(
                new Card(CardNumber.FOUR, CardShape.CLUB),
                new Card(CardNumber.ACE, CardShape.CLUB)
        );

        HandCards handCards = new HandCards(cards);

        // When
        int score = handCards.calculateScore();

        // Then
        assertThat(score).isEqualTo(15);
    }

    @Test
    @DisplayName("여러 장의 Ace에 대한 점수를 처리한다.")
    void judgeManyAceTest() {
        // Given
        List<Card> cards = List.of(
                new Card(CardNumber.EIGHT, CardShape.CLUB),
                new Card(CardNumber.ACE, CardShape.CLUB),
                new Card(CardNumber.ACE, CardShape.HEART)
        );
        HandCards handCards = new HandCards(cards);

        // When
        int score = handCards.calculateScore();

        // Then
        assertThat(score).isEqualTo(20);
    }

    @Test
    @DisplayName("21을 초과하면 버스트이다.")
    void judgeBustTest() {
        // Given
        List<Card> cards = List.of(
                new Card(CardNumber.JACK, CardShape.CLUB),
                new Card(CardNumber.FOUR, CardShape.CLUB),
                new Card(CardNumber.KING, CardShape.HEART)
        );
        HandCards handCards = new HandCards(cards);

        // When
        boolean isBust = handCards.isBust();

        // Then
        assertThat(isBust).isTrue();
    }

    @Test
    @DisplayName("21를 초과하지 않는다면 버스트가 아니다.")
    void notBustTest() {
        // Given
        List<Card> cards = List.of(
                new Card(CardNumber.JACK, CardShape.CLUB),
                new Card(CardNumber.KING, CardShape.HEART)
        );
        HandCards handCards = new HandCards(cards);

        // When
        boolean isBust = handCards.isBust();

        // Then
        assertThat(isBust).isFalse();
    }

    @Test
    @DisplayName("블랙잭인지 확인한다.")
    void blackJackTest() {
        // Given
        List<Card> cards = List.of(
                new Card(CardNumber.ACE, CardShape.CLUB),
                new Card(CardNumber.KING, CardShape.HEART)
        );
        HandCards handCards = new HandCards(cards);

        // When
        boolean isBlackJack = handCards.isBlackJack();

        // Then
        assertThat(isBlackJack).isTrue();
    }
}