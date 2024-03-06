package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("플레이어가 카드를 뽑아서 저장한다.")
    @Test
    void drawTest() {
        // given
        Name name = new Name("lini");
        Player player = new Player(name);
        Card card = new Card(Symbol.SPADE, Rank.QUEEN);
        Decks decks = new Decks();

        // when
        player.hit(decks);

        // then
        assertThat(player.getHand()).hasSize(1);
    }
}
