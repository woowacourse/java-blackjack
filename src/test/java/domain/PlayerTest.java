package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.TestUtil.createPlayer;
import static util.TestUtil.createSpadesCard;

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
    void 플레이어가_Bust인_경우_isBust가_True를_리턴한다() {
        // given
        Player player = new Player("봉구스");

        // when
        player.draw(new Card(Suit.CLUBS, Rank.KING));
        player.draw(new Card(Suit.CLUBS, Rank.QUEEN));
        player.draw(new Card(Suit.CLUBS, Rank.JACK));

        // then
        assertTrue(player.isBust());
    }

    @Test
    void 플레이어가_Bust가_아닌_경우_isBust가_False를_리턴한다() {
        // given
        Player player = new Player("봉구스");

        // when
        player.draw(new Card(Suit.CLUBS, Rank.KING));
        player.draw(new Card(Suit.CLUBS, Rank.QUEEN));

        // then
        assertFalse(player.isBust());
    }

    @Test
    void 플레이어가_블랙잭이면_isBlackjack이_true를_반환한다() {
        // given
        Player player = createPlayer("봉구스");

        // when
        player.draw(createSpadesCard(Rank.ACE));
        player.draw(createSpadesCard(Rank.QUEEN));

        // then
        assertTrue(player.isBlackjack());
    }

    @Test
    void 딜러가_블랙잭이면_isBlackjack이_false를_반환한다() {
        // given
        Player player = createPlayer("봉구스");

        // when
        player.draw(createSpadesCard(Rank.ACE));

        // then
        assertFalse(player.isBlackjack());
    }
}
