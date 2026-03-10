package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.TestUtil.createDealer;
import static util.TestUtil.createPlayer;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.card.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackJackTurnServiceTest {

    BlackJackTurnService blackJackTurnService;

    @BeforeEach
    void setUp() {
        blackJackTurnService = new BlackJackTurnService();
    }

    @Test
    void 플레이어가_정상적으로_Hit_하는_경우() {
        // given
        Deck deck = new Deck();
        Player player = createPlayer("봉구스");

        // when
        blackJackTurnService.playerHit(player, deck);

        // then
        assertEquals(1, player.getHand().getCards().size());
    }

    @Test
    void 딜러가_정상적으로_Hit_하는_경우() {
        // given
        Deck deck = new Deck();
        Dealer dealer = createDealer();

        // when
        blackJackTurnService.dealerHit(dealer, deck);

        // then
        assertEquals(1, dealer.getHand().getCards().size());
    }

    @Test
    void 딜러가_드로우_할_수_있는_경우() {
        // given
        Dealer dealer = createDealer(Rank.TWO, Rank.FIVE);

        // when, then
        assertTrue(blackJackTurnService.canDealerHit(dealer));
    }

    @Test
    void 딜러가_드로우_할_수_없는_경우() {
        // given
        Dealer dealer = createDealer(Rank.JACK, Rank.QUEEN, Rank.KING);

        // when, then
        assertFalse(blackJackTurnService.canDealerHit(dealer));
    }

    @Test
    void 플레이어가_드로우_할_수_있는_경우() {
        // given
        Player player = createPlayer("시오", Rank.TWO, Rank.FIVE);

        // when, then
        assertTrue(blackJackTurnService.canPlayerHit(player, "y"));
    }

    @Nested
    class 플레이어가_드로우_할_수_없는_경우 {
        @Test
        void 플레이어의_손패의_합이_21_이상인_경우() {
            // given
            Player player = createPlayer("시오", Rank.JACK, Rank.QUEEN, Rank.KING);

            // when, then
            assertFalse(blackJackTurnService.canPlayerHit(player, "y"));
        }

        @Test
        void 플레이어의_입력이_n인_경우() {
            // given
            Player player = createPlayer("시오", Rank.TWO, Rank.FIVE);

            // when, then
            assertFalse(blackJackTurnService.canPlayerHit(player, "n"));
        }
    }


    @Test
    void 손패의_합이_21보다_작은_경우() {
        // given
        Player player = createPlayer("시오", Rank.JACK);

        // when, then
        assertTrue(blackJackTurnService.isPlayerUnder21(player));
    }

    @Test
    void 손패의_합이_21보다_큰_경우() {
        // given
        Player player = createPlayer("시오", Rank.JACK, Rank.QUEEN, Rank.KING);

        // when, then
        assertFalse(blackJackTurnService.isPlayerUnder21(player));
    }
}
