package service;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import dto.BlackJackHandDto;
import dto.BlackJackInitStatusDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackTurnServiceTest {

    BlackJackTurnService blackJackTurnService;

    @BeforeEach
    void setUp(){
        blackJackTurnService = new BlackJackTurnService();
    }

    @Test
    void 플레이어가_정상적으로_Hit_하는_경우() {
        // given
        Deck deck  = new Deck();
        Player player = new Player("봉구스");

        // when
        blackJackTurnService.playerHit(player,deck);

        // then
        assertEquals(1, player.getHand().getCards().size());
    }

    @Test
    void 딜러가_정상적으로_Hit_하는_경우() {
        // given
        Deck deck  = new Deck();
        Dealer dealer = new Dealer();

        // when
        blackJackTurnService.dealerHit(dealer,deck);

        // then
        assertEquals(1, dealer.getHand().getCards().size());
    }

    @Test
    void 딜러가_드로우_할_수_있는_경우() {
        // given
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Suit.HEARTS, Rank.NUM2));
        dealer.draw(new Card(Suit.HEARTS, Rank.NUM5));

        // when, then
        assertTrue(blackJackTurnService.isDealerPossible(dealer));
    }

    @Test
    void 딜러가_드로우_할_수_없는_경우() {
        // given
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Suit.HEARTS, Rank.JACK));
        dealer.draw(new Card(Suit.HEARTS, Rank.QUEEN));
        dealer.draw(new Card(Suit.HEARTS, Rank.KING));

        // when, then
        assertFalse(blackJackTurnService.isDealerPossible(dealer));
    }

    @Test
    void 플레이어가_드로우_할_수_있는_경우() {
        // given
        Player player = new Player("시오");

        player.draw(new Card(Suit.HEARTS, Rank.NUM2));
        player.draw(new Card(Suit.HEARTS, Rank.NUM5));

        // when, then
        assertTrue(blackJackTurnService.isPlayerPossible(player, "y"));
    }

    @Nested
    class 플레이어가_드로우_할_수_없는_경우 {
        @Test
        void 플레이어의_손패의_합이_21_이상인_경우() {
            // given
            Player player = new Player("시오");

            player.draw(new Card(Suit.HEARTS, Rank.JACK));
            player.draw(new Card(Suit.HEARTS, Rank.QUEEN));
            player.draw(new Card(Suit.HEARTS, Rank.KING));

            // when, then
            assertFalse(blackJackTurnService.isPlayerPossible(player, "y"));
        }

        @Test
        void 플레이어의_입력이_n인_경우() {
            // given
            Player player = new Player("시오");

            player.draw(new Card(Suit.HEARTS, Rank.NUM2));
            player.draw(new Card(Suit.HEARTS, Rank.NUM5));

            // when, then
            assertFalse(blackJackTurnService.isPlayerPossible(player, "n"));
        }
    }

    @Test
    void 손패_Dto를_생성하는_경우() {
        //given
        Player player = new Player("봉구스");

        // when
        BlackJackHandDto blackJackHandDto = blackJackTurnService.createHandDto(player);
        // then
        assertNotNull(blackJackHandDto);
    }
}