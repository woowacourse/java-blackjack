package blackjack.domain.participant;

import blackjack.domain.card.Denomination;
import org.junit.jupiter.api.Test;

import static blackjack.fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    void 전달받은_카드를_패에_추가한다() {
        Player player = new Player("pobi");

        player.add(카드());

        assertThat(player.getCardHand()).hasSize(1);
    }

    @Test
    void 카드의_합을_계산한다() {
        Player player = new Player("pobi");

        player.add(카드(Denomination.KING));
        player.add(카드(Denomination.SIX));

        int result = player.calculateScore();

        assertThat(result).isEqualTo(16);
    }
}
