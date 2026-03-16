package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import exception.ErrorMessage;
import factory.CardFactory;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameTest {

    @Test
    void 게임_시작시_플레이어와_딜러에게_두장씩_분배한다() {
        // given
        Deck deck = mock(Deck.class);
        when(deck.drawCard()).thenReturn(
                new Card(Suit.HEARTS, Rank.NUM2),
                new Card(Suit.HEARTS, Rank.NUM3),
                new Card(Suit.CLUBS, Rank.NUM4),
                new Card(Suit.CLUBS, Rank.NUM5),
                new Card(Suit.SPADES, Rank.NUM6),
                new Card(Suit.SPADES, Rank.NUM7)
        );

        // when
        Game game = new Game(List.of(new Name("시오"), new Name("봉구스")), deck);

        // then
        assertAll(
                () -> assertEquals(2, game.getPlayerHandSize(game.getPlayers().get(0))),
                () -> assertEquals(2, game.getPlayerHandSize(game.getPlayers().get(1))),
                () -> assertEquals(2, game.getDealerHandSize())
        );
    }

    @Test
    void 플레이어가_Hit하면_카드를_한장_추가로_받는다() {
        // given
        Deck deck = mock(Deck.class);
        when(deck.drawCard()).thenReturn(
                new Card(Suit.HEARTS, Rank.NUM2),
                new Card(Suit.HEARTS, Rank.NUM3),
                new Card(Suit.CLUBS, Rank.NUM4),
                new Card(Suit.CLUBS, Rank.NUM5),
                new Card(Suit.SPADES, Rank.NUM6),
                new Card(Suit.SPADES, Rank.NUM7),
                new Card(Suit.DIAMONDS, Rank.NUM8)
        );
        Game game = new Game(List.of(new Name("시오"), new Name("봉구스")), deck);
        Player player = game.getPlayers().get(0);

        // when
        game.hitPlayer(player);

        // then
        assertEquals(3, player.getHandSize());
    }

    @Test
    void 딜러가_Hit하면_카드를_한장_추가로_받는다() {
        // given
        Deck deck = mock(Deck.class);
        when(deck.drawCard()).thenReturn(
                new Card(Suit.HEARTS, Rank.NUM2),
                new Card(Suit.HEARTS, Rank.NUM3),
                new Card(Suit.CLUBS, Rank.NUM4),
                new Card(Suit.CLUBS, Rank.NUM5),
                new Card(Suit.SPADES, Rank.NUM6),
                new Card(Suit.SPADES, Rank.NUM7),
                new Card(Suit.DIAMONDS, Rank.NUM8)
        );
        Game game = new Game(List.of(new Name("시오"), new Name("봉구스")), deck);

        // when
        game.hitDealer();

        // then
        assertEquals(3, game.getDealer().getHandSize());
    }

    @Test
    void 딜러_점수가_17미만이면_카드를_더_받는다() {
        // given
        Deck deck = mock(Deck.class);
        when(deck.drawCard()).thenReturn(
                new Card(Suit.HEARTS, Rank.NUM2),
                new Card(Suit.HEARTS, Rank.NUM3),
                new Card(Suit.CLUBS, Rank.NUM4),
                new Card(Suit.CLUBS, Rank.NUM5),
                new Card(Suit.SPADES, Rank.NUM6),
                new Card(Suit.SPADES, Rank.NUM7)
        );

        // when
        Game game = new Game(List.of(new Name("시오"), new Name("봉구스")), deck);

        // then
        assertTrue(game.dealerCanHit());
    }

    @Test
    void 딜러_점수가_17이상이면_카드를_더_받지_않는다() {
        // given
        Deck deck = mock(Deck.class);
        when(deck.drawCard()).thenReturn(
                new Card(Suit.HEARTS, Rank.NUM2),
                new Card(Suit.HEARTS, Rank.NUM3),
                new Card(Suit.CLUBS, Rank.NUM4),
                new Card(Suit.CLUBS, Rank.NUM5),
                new Card(Suit.SPADES, Rank.NUM10),
                new Card(Suit.SPADES, Rank.NUM7)
        );

        // when
        Game game = new Game(List.of(new Name("시오"), new Name("봉구스")), deck);

        // then
        assertFalse(game.dealerCanHit());
    }

    @Test
    void 플레이어의_이름이_중복된다면_예외를_발생한다() {
        // given
        List<Name> names = List.of(new Name("시오"), new Name("시오"));

        // when, then
        assertThatThrownBy(() -> new Game(names, new Deck(CardFactory.createDeck())))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATE_NAME.getMessage());
    }

    @Test
    void 게임_진행_후_승패_결과를_올바르게_계산한다() {
        // given
        Deck deck = mock(Deck.class);
        when(deck.drawCard()).thenReturn(
                new Card(Suit.HEARTS, Rank.NUM2),
                new Card(Suit.HEARTS, Rank.NUM3),
                new Card(Suit.CLUBS, Rank.NUM4),
                new Card(Suit.CLUBS, Rank.NUM5),
                new Card(Suit.SPADES, Rank.NUM6),
                new Card(Suit.SPADES, Rank.NUM7)
        );
        Game game = new Game(List.of(new Name("시오"), new Name("봉구스")), deck);
        Player firstPlayer = game.getPlayers().get(0);
        Player secondPlayer = game.getPlayers().get(1);

        // when
        Map<Player, WinningStatus> winningStatusMap = game.calculateAllResults();

        // then
        assertAll(
                () -> assertEquals(2, winningStatusMap.size()),
                () -> assertEquals(WinningStatus.LOSE, winningStatusMap.get(firstPlayer)),
                () -> assertEquals(WinningStatus.LOSE, winningStatusMap.get(secondPlayer))
        );
    }
}