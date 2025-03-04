package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void 이름으로부터_플레이어를_생성한다(){
        // given
        final String name = "pobi";

        // when & then
        assertThatCode(() -> Player.of(name))
                .doesNotThrowAnyException();
    }

    @Test
    void 카드를_한_장_씩_받아_들고있는다() {
        // given
        final Player player = Player.of("pobi");
        final Card card = Card.of(
                TrumpNumber.ACE, TrumpShape.CLUB
        );

        // when & then
        assertThatCode(() -> player.receive(card))
                .doesNotThrowAnyException();
    }
}
