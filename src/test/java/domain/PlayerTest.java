package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void 플레이어가_카드를_뽑으면_손패의_크기가_1_증가한다() {
        // given
        Player player = new Player("봉구스");

        // when
        player.draw(new Card(Suit.CLUBS, Rank.ACE));

        // then
        assertEquals(1, player.getHandSize());
    }

    @Test
    void 플레이어의_카드_점수_합이_21을_초과하면_버스트_상태가_된다() {
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