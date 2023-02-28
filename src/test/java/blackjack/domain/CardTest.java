package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("Ace인 경우 isAce() 테스트")
    void isAceTest_ace() {
        // given
        Card card = new Card(Number.ACE, Pattern.HEART);

        // when
        boolean result = card.isAce();

        // then
        Assertions.assertThat(result).isTrue();
     }

     @Test
     @DisplayName("Ace가 아닌 경우 isAce() 테스트")
     void isAceTest_notAce() {
         // given
         Card card = new Card(Number.K, Pattern.HEART);

         // when
         boolean result = card.isAce();

         // then
         Assertions.assertThat(result).isFalse();
      }
}
