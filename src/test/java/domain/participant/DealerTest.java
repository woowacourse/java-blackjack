package domain.participant;

import java.util.List;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    @Test
    @DisplayName("딜러는 점수의 합이 16 이하이면, 카드를 한 장 받는다.")
    void receiveCardTest() {
        // Given
        List<Card> cards = List.of(
                new Card(CardNumber.EIGHT, CardShape.CLUB),
                new Card(CardNumber.SIX, CardShape.CLUB)
        );

        Dealer dealer = new Dealer();

        dealer.drawInitialCards(cards);
        // When
        boolean isReceiveCard = dealer.isReceiveCard();

        // Then
        assertThat(isReceiveCard).isTrue();
    }

    @Test
    @DisplayName("딜러는 점수의 합이 17 이상이라면 카드를 받지 않는다..")
    void notReceiveCardTest() {
        // Given
        List<Card> cards = List.of(
                new Card(CardNumber.SIX, CardShape.CLUB),
                new Card(CardNumber.ACE, CardShape.CLUB)
        );

        Dealer dealer = new Dealer();

        dealer.drawInitialCards(cards);
        // When
        boolean isReceiveCard = dealer.isReceiveCard();

        // Then
        assertThat(isReceiveCard).isFalse();
    }
}

