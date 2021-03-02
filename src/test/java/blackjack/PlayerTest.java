package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("참가자가 잘 생성되는지 확인")
    void create() {
        assertThatCode(() -> new Player("bada"))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("참가자가 카드를 받았는지 확인")
    void receiveCard() {
        Player player = new Player("joel");
        player.receiveCard(new Card(CardNumber.ACE, CardType.CLOVER));
        assertThat(player.getCardCount()).isEqualTo(1);
    }
}
