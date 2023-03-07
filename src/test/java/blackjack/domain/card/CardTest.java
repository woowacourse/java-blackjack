package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("Ace인 경우 isAce() 테스트")
    void isAceTest_ace() {
        // given
        Card card = new Card(Number.ACE, Suit.HEART);

        // when
        boolean result = card.isAce();

        // then
        assertThat(result).isTrue();
     }

     @Test
     @DisplayName("Ace가 아닌 경우 isAce() 테스트")
     void isAceTest_notAce() {
         // given
         Card card = new Card(Number.K, Suit.HEART);

         // when
         boolean result = card.isAce();

         // then
         assertThat(result).isFalse();
      }
}
