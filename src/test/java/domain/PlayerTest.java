package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 드로우가_정상적으로_동작하는_경우() {
        // given
        Player player = new Player("봉구스");

        // when
        player.draw(new Card(Suit.CLUBS, Rank.ACE));

        // then
        assertEquals(1, player.getHand().getCards().size());
    }

    @Test
    void 플레이어가_Burst인_경우() {
        // given
        Player player = new Player("봉구스");

        // when
        player.draw(new Card(Suit.CLUBS, Rank.KING));
        player.draw(new Card(Suit.CLUBS, Rank.QUEEN));
        player.draw(new Card(Suit.CLUBS, Rank.JACK));

        // then
        assertTrue(player.isBurst());
    }
}
