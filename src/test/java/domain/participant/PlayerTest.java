package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.Card;
import domain.card.TrumpNumber;
import domain.card.TrumpShape;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void 이름으로부터_플레이어를_생성한다() {
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

    @Test
    void 플레이어의_점수를_반환한다() {
        // given
        final Player player = Player.of("pobi");
        final Card card = Card.of(
                TrumpNumber.ACE, TrumpShape.CLUB
        );
        player.receive(card);

        // when
        int score = player.getScore();

        // then
        assertThat(score).isEqualTo(11);
    }
}
