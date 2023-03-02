package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class PlayerTest {
    
    @Test
    @DisplayName("이름의 길이는 최소 1글자에서 최대 5글자이다.")
    void validateNameLength() {
        // given
        String name = "millie";

        // expect
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Player(name)
        ).withMessage("[ERROR] 이름 길이는 최소 1글자에서 최대 5글자 입니다.");
     }

     @Test
     @DisplayName("카드를 새로 받는 기능 테스트")
     void addCardTest() {
         // given
         Player player = new Player("doggy");
         List<Card> cards = player.getCards().getCards();

         // when
         player.receiveCard(new Card(Number.ACE, Pattern.HEART));

         // then
         assertThat(cards.size()).isEqualTo(1);
      }
}
