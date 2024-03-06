package domain;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    void 전달받은_카드를_패에_추가한다() {
        Player player = new Player("pobi");

        player.add(카드());

        assertThat(player).extracting("cards")
                .extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(1);
    }
}
