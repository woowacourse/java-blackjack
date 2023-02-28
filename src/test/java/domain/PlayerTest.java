package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    @DisplayName("이름을 받아 플레이어를 생성한다")
    void createPlayerTest() {
        Player player = new Player("boxster");

        assertThat(player.getName()).isEqualTo("boxster");
    }

    @Test
    @DisplayName("카드를 받아 플레이어 카드에 추가한다")
    void addPlayerCardsTest() {
        Player player = new Player("jamie");
        Card card = new Card(CardSuit.HEART, CardNumber.ACE);

        player.addCard(card);

        assertThat(player.getCards()).contains(card);
    }
}
