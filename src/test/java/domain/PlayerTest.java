package domain;

import domain.card.Cards;
import domain.participant.BetAmount;
import domain.participant.Participant;
import domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


class PlayerTest {
    Participant player;

    @BeforeEach
    void setUp() {
        player = new Player("pobi", new BetAmount(BigDecimal.valueOf(1000)));
    }
    @Test
    void 플레이어의_카드의_합이_21_미만일_경우_플레이어는_카드를_받을_수_있다() {
        Cards cards = CardFixture.seventeenCards();

        cards.cards().forEach(player::receive);
        boolean canDraw = player.canDraw();

        assertThat(canDraw).isTrue();
    }

    @Test
    void 플레이어의_카드의_합이_21_이상일_경우_플레이어는_카드를_받을_수_없다() {
        Cards cards = CardFixture.twentyTwoCards();

        cards.cards().forEach(player::receive);
        boolean canDraw = player.canDraw();

        assertThat(canDraw).isFalse();

    }
}
