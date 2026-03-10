package domain.participant;

import java.util.List;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import org.assertj.core.api.Assertions;
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
                new Card(CardNumber.FOUR, CardShape.CLUB)
        );

        HandCards handCards = new HandCards(cards);
        Dealer dealer = new Dealer(handCards);

        // When
        boolean isReceiveCard = dealer.isReceiveCard();

        // Then
        assertThat(isReceiveCard).isTrue();
    }
}
