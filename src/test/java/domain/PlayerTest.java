package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player createPlayerWithCards(String name, Card... cards) {
        Player player = new Player(new Name(name));
        for (Card card : cards) {
            player.draw(card);
        }
        return player;
    }

    @Test
    void 플레이어가_카드를_뽑으면_손패의_크기가_1_증가한다() {
        // given
        Player player = new Player(new Name("봉구스"));

        // when
        player.draw(new Card(Suit.CLUBS, Rank.ACE));

        // then
        assertEquals(1, player.getHandSize());
    }

    @Test
    void 플레이어는_초기상태에서_hit할수있다() {
        Player player = new Player(new Name("시오"));
        assertTrue(player.canHit());
    }

    @Test
    void 플레이어가_stay하면_hit할수없다() {
        Player player = createPlayerWithCards("봉구스",
                new Card(Suit.CLUBS, Rank.NUM2),
                new Card(Suit.DIAMONDS, Rank.NUM3)
        );

        player.stay();

        assertFalse(player.canHit());
    }

    @Test
    void 플레이어가_블랙잭이면_hit할수없다() {
        Player player = createPlayerWithCards("봉구스",
                new Card(Suit.CLUBS, Rank.ACE),
                new Card(Suit.DIAMONDS, Rank.KING)
        );

        assertAll(
                () -> assertTrue(player.isBlackJack()),
                () -> assertFalse(player.canHit())
        );
    }

    @Test
    void 플레이어가_버스트면_hit할수없다() {
        Player player = createPlayerWithCards("봉구스",
                new Card(Suit.CLUBS, Rank.KING),
                new Card(Suit.DIAMONDS, Rank.QUEEN),
                new Card(Suit.HEARTS, Rank.JACK)
        );

        assertAll(
                () -> assertTrue(player.isBurst()),
                () -> assertFalse(player.canHit())
        );
    }
}