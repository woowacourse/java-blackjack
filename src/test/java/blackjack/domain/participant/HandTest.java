package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class HandTest {
    @Test
    @DisplayName("Hand가 잘 생성되었는지 확인")
    void constructorTest() {
        assertThatCode(() -> new Hand())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Hand에 새로운 카드가 추가되는지 확인")
    void receiveCardTest() {
        final Hand hand = new Hand();
        hand.receiveCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
        assertThat(hand.getHand()).hasSize(1);
    }

    @Test
    @DisplayName("Hand에 추가된 카드들의 합을 확인")
    void calculateScoreTest() {
        final Hand hand = new Hand();
        hand.receiveCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
        hand.receiveCard(new Card(CardNumber.SEVEN, CardType.CLOVER));
        assertThat(hand.calculateScore()).isEqualTo(15);
    }
}
