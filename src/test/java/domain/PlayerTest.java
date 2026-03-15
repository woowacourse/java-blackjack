package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Participant;
import domain.participant.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class PlayerTest {

    @Test
    void 플레이어의_카드의_합이_21_미만일_경우_플레이어는_카드를_받을_수_있다() {
        Participant player = new Player("pobi");

        List<Card> cards = List.of(
                new Card(Rank.TWO, Suit.HEART),
                new Card(Rank.SEVEN, Suit.CLOVER)
        );

        cards.forEach(player::receive);
        boolean canDraw = player.canDraw();

        assertThat(canDraw).isTrue();
    }

    @Test
    void 플레이어의_카드의_합이_21_이상일_경우_플레이어는_카드를_받을_수_없다() {
        Participant player = new Player("pobi");

        List<Card> cards = List.of(
                new Card(Rank.SEVEN, Suit.HEART),
                new Card(Rank.SEVEN, Suit.CLOVER),
                new Card(Rank.SEVEN, Suit.SPADE)
        );

        cards.forEach(player::receive);
        boolean canDraw = player.canDraw();

        assertThat(canDraw).isFalse();

    }
}
