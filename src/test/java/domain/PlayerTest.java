package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.TestUtil.createPlayer;
import static util.TestUtil.createSpadesCard;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Player;
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
    void Bust인_경우_isBust가_True를_리턴한다() {
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
    void 블랙잭이면_isBlackjack이_true를_반환한다() {
        // given
        Player player = createPlayer("봉구스");

        // when
        player.draw(createSpadesCard(Rank.ACE));
        player.draw(createSpadesCard(Rank.TWO));
        player.draw(createSpadesCard(Rank.EIGHT));

        // then
        assertTrue(player.isBlackjack());
    }

    @Test
    void 블랙잭이_아니면_isBlackjack이_false를_반환한다() {
        // given
        Player player = createPlayer("봉구스");

        // when
        player.draw(createSpadesCard(Rank.ACE));

        // then
        assertFalse(player.isBlackjack());
    }

    @Test
    void 첫턴에_블랙잭이면_isBlackjackAtFirst가_true를_반환한다() {
        // given
        Player player = createPlayer("봉구스");

        // when
        player.draw(createSpadesCard(Rank.ACE));
        player.draw(createSpadesCard(Rank.JACK));

        // then
        assertTrue(player.isBlackjackAtFirst());
    }

    @Test
    void 첫턴에_블랙잭이_아니면_isBlackjackAtFirst가_false를_반환한다() {
        // given
        Player player = createPlayer("봉구스");

        // when
        player.draw(createSpadesCard(Rank.ACE));
        player.draw(createSpadesCard(Rank.TWO));

        // then
        assertFalse(player.isBlackjackAtFirst());
    }
}
