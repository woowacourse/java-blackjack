import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardHandTest {
    @DisplayName("점수 계산 - Ace가 없는 경우")
    @Test
    void ACE가_없는_경우_카드_합() {
        CardHand cardHand = new CardHand();

        cardHand.add(new Card(Symbol.DIAMOND, CardNumber.EIGHT));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.JACK));
        cardHand.add(new Card(Symbol.SPADE, CardNumber.TWO));

        assertThat(cardHand.calculateScore()).isEqualTo(20);
    }

    @DisplayName("점수 계산 - Ace가 1로 사용되는 경우")
    @Test
    void ACE가_1로_사용되는_경우_카드_합() {
        CardHand cardHand = new CardHand();

        cardHand.add(new Card(Symbol.DIAMOND, CardNumber.ACE));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.JACK));
        cardHand.add(new Card(Symbol.SPADE, CardNumber.TWO));

        assertThat(cardHand.calculateScore()).isEqualTo(13);
    }

    @DisplayName("점수 계산 - Ace가 11로 사용되는 경우")
    @Test
    void ACE가_11로_사용되는_경우_카드_합() {
        CardHand cardHand = new CardHand();

        cardHand.add(new Card(Symbol.DIAMOND, CardNumber.ACE));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.EIGHT));
        cardHand.add(new Card(Symbol.SPADE, CardNumber.TWO));

        assertThat(cardHand.calculateScore()).isEqualTo(21);
    }

    @DisplayName("점수 계산 - Ace가 1개는 1로, 1개는 11로 사용되는 경우")
    @Test
    void ACE가_2개인_경우_카드_합() {
        CardHand cardHand = new CardHand();

        cardHand.add(new Card(Symbol.DIAMOND, CardNumber.ACE));
        cardHand.add(new Card(Symbol.SPADE, CardNumber.NINE));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.ACE));

        assertThat(cardHand.calculateScore()).isEqualTo(21);
    }
}